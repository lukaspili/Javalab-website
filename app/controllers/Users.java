package controllers;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import models.users.Campus;
import models.users.Promotion;
import models.users.User;
import play.Play;
import play.libs.OpenID;
import play.mvc.Before;
import service.UserService;
import validation.EnhancedValidator;
import controllers.filters.AuthFilter;
import controllers.filters.security.LoggedAccess;
import controllers.filters.security.PublicAccess;
import controllers.helpers.controller.PageHelper;
import controllers.logicals.LogicController;

/**
 * @author Lukasz Piliszczuk <lukasz.piliszczuk AT zenika.com>
 */

public class Users extends LogicController {

    private static final String SUPINFO_OPEN_ID = "https://id.supinfo.com/me/";

    @Inject
    private static UserService userService;

    private static PageHelper pageHelper;

    @Before
    public static void before() {
        pageHelper = new PageHelper("users", renderArgs);
    }

    @PublicAccess(only = true)
    public static void login() {
        render();
    }

    @PublicAccess(only = true)
    public static void authenticate(User user) {

        if (OpenID.isAuthenticationResponse()) {

            OpenID.UserInfo openIdUser = OpenID.getVerifiedID();
            if (null == openIdUser) {
                login();
            }

        } else {
            if (!OpenID.id(SUPINFO_OPEN_ID + user.idBooster).verify()) {
                login();
            }
        }
    }

    @PublicAccess(only = true)
    public static void authenticateDev(String idBooster) {

        if (Play.mode != Play.Mode.DEV) {
            login();
        }
        User userFromDb = User.find("byIdBooster", idBooster).first();

        if (null == userFromDb) {
            flashError("users.login.authentication.failure");
        } else {
            AuthFilter.loginUser(userFromDb);
        }

        Dashboard.index();
    }

    @LoggedAccess
    public static void logout() {
        AuthFilter.logoutUser();
        Dashboard.index();
    }


	@LoggedAccess
	public static void modify(String firstName, String lastName, String campus) {
		User user = AuthFilter.getCurrentUser();
		user.firstName = firstName;
		user.lastName = lastName;
		user.campus = Campus.valueOf(campus);
		user.save();
		flash.success("Votre profile a été modifié !");
		profile();
	}
	
	@LoggedAccess
	public static void details(String idBooster) {
		User user = User.find("byIdBooster", idBooster).first();
		render(user);
	}

	
	private static List<Campus> getCampusAsList() {
		List<Campus> campusList = new ArrayList<Campus>();
		for(Campus campus : Campus.values()) {
			campusList.add(campus);
		}
		return campusList;
	}
	
	private static List<Promotion> getPromotionAsList() {
		List<Promotion> promotionList = new ArrayList<Promotion>();
		for(Promotion promotion : Promotion.values()) {
			promotionList.add(promotion);
		}
		return promotionList;
	}


    @LoggedAccess
    public static void modify(User user, String idBooster, Promotion promotion,
                              Campus campus) {

        User userModify = User.find("byIdBooster", user.idBooster).first();
        System.out.println(userModify);

        userModify.promotion = promotion;
        userModify.campus = campus;
        userModify.save();
        flash.success("Votre profile a été modifié !");

        profile();

    }

    @LoggedAccess
    public static void profile() {
    	User user = AuthFilter.getCurrentUser();
		List<Promotion> promotionList = getPromotionAsList();
		render(user, promotionList);
    }
    

	@PublicAccess(only = true)
	public static void inscription() {
		List<Promotion> promotionList = getPromotionAsList();
		List<Campus> campusList = getCampusAsList();
		render(promotionList, campusList);
	}
	
	@PublicAccess(only = true)
	public static void create(User user) {
		EnhancedValidator validator = validator();
		validator.validate(user).require("idBooster", "firstName", "lastName", "promotion", "campus");
		if(validator.hasErrors()) {
			render("Users/inscription.html", user);
		}
		User createdUser = userService.create(user);
		flash.success("Merci " + createdUser.firstName + createdUser.lastName + ", vous êtes bien enregistré");
		Dashboard.index();
	}
	
}
