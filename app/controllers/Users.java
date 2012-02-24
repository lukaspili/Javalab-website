package controllers;

import controllers.abstracts.AppController;
import controllers.filters.userfirstlogin.UserFirstLogin;
import controllers.security.Auth;
import controllers.security.LoggedAccess;
import controllers.security.PublicAccess;
import helper.Logger;
import models.users.Campus;
import models.users.Profile;
import models.users.Promotion;
import models.users.User;
import play.Play;
import play.data.validation.Required;
import play.libs.OpenID;
import play.mvc.Http;
import play.mvc.Router;
import service.UserService;
import validation.EnhancedValidator;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Lukasz Piliszczuk <lukasz.piliszczuk AT zenika.com>
 */

public class Users extends AppController {

    private static final Logger logger = Logger.getLoggerFor(Users.class);

    @Inject
    private static UserService userService;

    @PublicAccess(only = true)
    public static void login() {
        render();
    }

    @PublicAccess(only = true)
    public static void authenticate(@Required String idBooster) {

        if (!validation.errorsMap().isEmpty()) {
            render("Users/login.html", idBooster);
        }

        if (OpenID.isAuthenticationResponse()) {

            logger.debug("Received OpenID authentication response");

            OpenID.UserInfo openIdUser = OpenID.getVerifiedID();

            if (null == openIdUser) {
                logger.debug("OpenID verified ID is null, redirect to login");
                login();
            }

            logger.debug("OpenID valid authentication response for idBooster : %s", idBooster);

            User user = userService.login(idBooster);
            Auth.loginUser(user);

            Dashboard.index();
        }

        logger.debug("Building OpenID authentication request");

        String openidUrl = Play.configuration.getProperty("openid.url") + idBooster;

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("idBooster", idBooster);
        String returnTo = Http.Request.current().getBase() + Router.reverse(Http.Request.current().action, params);

        logger.debug("OpenID authentication request to : %s", openidUrl);
        logger.debug("OpenID authention request return to : %s", returnTo);

        OpenID openID = OpenID.id(openidUrl).returnTo(returnTo);

        if (!openID.verify()) {
            logger.debug("Invalid OpenID request");
            login();
        }
    }

    @LoggedAccess
    public static void logout() {
        Auth.logoutUser();
        Dashboard.index();
    }


    @LoggedAccess
    public static void modify(String firstName, String lastName, String campus) {
        User user = Auth.getCurrentUser();
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
        for (Campus campus : Campus.values()) {
            campusList.add(campus);
        }
        return campusList;
    }

    private static List<Promotion> getPromotionAsList() {
        List<Promotion> promotionList = new ArrayList<Promotion>();
        for (Promotion promotion : Promotion.values()) {
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
        User user = Auth.getCurrentUser();
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
	
	@LoggedAccess
	public static void candidate() {
		User user = Auth.getCurrentUser();
		user.profile = Profile.CANDIDATE;
		user.save();
		render(user);
	}
	
}
