package controllers.filters.userfirstlogin;

import controllers.Dashboard;
import controllers.Users;
import controllers.security.Auth;
import controllers.security.PublicAccess;
import helper.Logger;
import models.users.User;
import play.mvc.Before;
import play.mvc.Controller;

/**
 * @author Lukasz Piliszczuk <lukasz.piliszczuk AT zenika.com>
 */
public class UserFirstLoginFilter extends Controller {

    private static final Logger logger = Logger.getLoggerFor(UserFirstLoginFilter.class);

    @Before(priority = 2)
    public static void checkFirstLogin() {

        // user is not logged
        if (!Auth.isLogged()) {
            return;
        }

        User user = Auth.getCurrentUser();

        UserFirstLogin firstLoginAnnotation = getActionAnnotation(UserFirstLogin.class);

        // action is not for first logged users
        if (null == firstLoginAnnotation) {

            // user is first logged
            if (user.firstLogin) {
                Users.firstLogin();
            }
        }

        // action is for the first logged users
        else {

            // user is not first logged user and action is only for first logged users
            if (!user.firstLogin && firstLoginAnnotation.only()) {
                Dashboard.index();
            }
        }
    }
}
