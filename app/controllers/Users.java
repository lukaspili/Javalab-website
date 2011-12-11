package controllers;

import controllers.filters.AuthFilter;
import controllers.filters.security.LoggedAccess;
import controllers.filters.security.PublicAccess;
import controllers.helpers.controller.PageHelper;
import controllers.logicals.LogicController;
import models.users.Campus;
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

    /**
     * Members list
     */
    public static void list() {

    }

    public static void list(Campus campus) {

    }
}
