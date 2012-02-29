package controllers.filters.userfirstlogin;

import controllers.Dashboard;
import controllers.Users;
import controllers.security.Auth;
import controllers.security.PublicAccess;
import play.mvc.Before;
import play.mvc.Controller;

/**
 * @author Lukasz Piliszczuk <lukasz.piliszczuk AT zenika.com>
 */
public class UserFirstLoginFilter extends Controller {

    @Before
    public static void checkFirstLogin() {

        // user is not logged or the action is public, ignore
        if (!Auth.isLogged() || null != getActionAnnotation(PublicAccess.class)) {
            return;
        }

        UserFirstLogin firstLoginAnnotation = getActionAnnotation(UserFirstLogin.class);

        // user is not active and action is not for first logged users
        if (null == firstLoginAnnotation && Auth.getCurrentUser().isTheFirstLogin()) {
            Users.firstLogin();
        }

        // user is active and action is for first logged users only
        if (null != firstLoginAnnotation && firstLoginAnnotation.only() && Auth.getCurrentUser().isTheFirstLogin()) {
            Dashboard.index();
        }
    }
}
