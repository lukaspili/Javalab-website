package controllers;

import java.util.Set;

import controllers.filters.AuthFilter;
import controllers.filters.security.LoggedAccess;
import controllers.filters.security.PublicAccess;
import controllers.helpers.controller.PageHelper;
import controllers.logicals.LogicController;
import models.events.News;
import models.events.Project;
import models.events.Talk;
import models.users.Campus;
import models.users.Profile;
import models.users.Promotion;
import models.users.User;
import play.Logger;
import play.mvc.Before;
import validation.EnhancedValidator;

/**
 * @author Lukasz Piliszczuk <lukasz.piliszczuk AT zenika.com>
 */

public class Users extends LogicController {

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

        EnhancedValidator validator = validator();

        if (validator.validate(user).require("idBooster").hasErrors()) {
            render("Users/login.html", user);
        }

        User userFromDb = User.find("byIdBooster", user.idBooster).first();

        if (null == userFromDb) {
            flashErrorSamePage("users.login.authentication.failure");
            render("Users/login.html", user);
        }

        AuthFilter.loginUser(userFromDb);

        Dashboard.index();
    }

    @LoggedAccess
    public static void logout() {
        Logger.debug("foobar");
        AuthFilter.logoutUser();
        Dashboard.index();
    }

    @LoggedAccess
    public static void profile() {

        User user = AuthFilter.getCurrentUser();

        render(user);
    }

    @PublicAccess(only = true)
    public static void inscription(){
    	render();
    }
    @PublicAccess(only = true)
    public static void nouveau(String idBooster, String firstName, String lastName,
			Promotion promotion, Campus campus){
    	
      new User(idBooster, firstName, lastName,promotion, null, campus, null, null, null).save();
      flash.error("Merci " + firstName + lastName + " vous êtes bien enregistré");
     
      render();

    }
    
    @LoggedAccess
    public static void modify(User user,String idBooster, String firstName, String lastName,
			Promotion promotion, Campus campus) {
    	
    	User userModify = User.find("byIdBooster", user.idBooster).first();
    	
    	userModify.setFirstName(firstName);
    	userModify.setLastName(lastName);
    	userModify.setPromotion(promotion);
    	userModify.setCampus(campus);
    	userModify.save();
    	flash.error("Votre profile a été modifié !");
    	
    	profile();
    	
    }
    /**
     * Members list
     */
    public static void list() {

    }

    public static void list(Campus campus) {

    }
}
