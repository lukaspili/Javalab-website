package controllers.security;

import controllers.Dashboard;
import controllers.Users;
import controllers.abstracts.UtilController;
import controllers.helpers.view.AuthHelper;
import helper.Logger;
import models.users.Profile;
import models.users.User;
import play.i18n.Messages;
import play.mvc.Before;
import play.mvc.Util;
import play.mvc.With;

/**
 * @author Lukasz Piliszczuk <lukasz.piliszczuk AT zenika.com>
 */
@With(AuthHelper.class)
public class Auth extends UtilController {

    private static final Logger logger = Logger.getLoggerFor(Auth.class);

    private static final String CURRENT_USER = "currentUser";

    @Before(priority = 0)
    public static void checkUser() {

        logger.debug("Check if user is logged");

        if (!isLogged()) {
            logger.debug("User is not logged");
            return;
        }

        Long id;

        try {
            id = Long.valueOf(session.get(CURRENT_USER));
        } catch (NumberFormatException e) {
            logger.error("Invalid session id : %s", session.get(CURRENT_USER));
            logoutUser();
            return;
        }

        User user = User.findById(id);

        if (null == user) {
            logger.error("User don't exists with session id : %s ", id);
            logoutUser();
        }

        logger.debug("Logged user : %s", user);
        renderArgs.put(CURRENT_USER, user);
    }

    @Before(priority = 1)
    public static void checkAccess() {

        logger.debug("Check access");

        // check public access first

        logger.debug("Check public access");
        boolean isPublic = false;

        PublicAccess publicAccess = getActionAnnotation(PublicAccess.class);
        if (null != publicAccess) {
            isPublic = true;
            checkAccess(publicAccess);
        }

        publicAccess = getControllerInheritedAnnotation(PublicAccess.class);
        if (null != publicAccess) {
            isPublic = true;
            checkAccess(publicAccess);
        }

        if (isPublic) {
            logger.debug("Access allowed for both guest and logged users");
            return;
        }

        // check logged access next

        logger.debug("Check logged access");
        boolean isAccessDefined = false;

        if (!isLogged()) {
            logger.debug("Access denied for guest users");
            Users.login();
        }

        logger.debug("Check profile access");

        LoggedAccess loggedAccess = getActionAnnotation(LoggedAccess.class);
        if (loggedAccess != null) {
            isAccessDefined = true;
            checkAccess(loggedAccess);
        }

        loggedAccess = getControllerInheritedAnnotation(LoggedAccess.class);
        if (loggedAccess != null) {
            isAccessDefined = true;
            checkAccess(loggedAccess);
        }

        // no access defined on the action
        if (!isAccessDefined) {
            denyAccess();
        }
    }

    @Util
    public static void checkAccess(PublicAccess publicAccess) {

        if (publicAccess.only() && isLogged()) {
            logger.debug("Public access allowed only, redirect to dashboard");
            Dashboard.index();
        }
    }

    @Util
    public static void checkAccess(LoggedAccess loggedAccess) {

        Profile profile = getCurrentUser().profile;

        logger.debug("Check access for current user with profile %s", profile);

        if (loggedAccess.only() && loggedAccess.value() == profile) {
            return;
        } else if (!loggedAccess.only() && profile.hasAccessOn(loggedAccess.value())) {
            return;
        }

        denyAccess();
    }

    @Util
    public static void denyAccess() {

        logger.debug("Access denied");

        String profile = (isLogged() ? getCurrentUser().profile.getLabel() : Messages.get("profile.guest"));
        flashError("auth.logged.denied_for_profile", profile);

        Dashboard.index();
    }

    @Util
    public static User getCurrentUser() {
        return renderArgs.get(Auth.CURRENT_USER, User.class);
    }

    @Util
    public static boolean isLogged() {
        return session.contains(CURRENT_USER);
    }

    @Util
    public static void logoutUser() {

        logger.debug("Logout current user : %s", getCurrentUser());

        session.remove(CURRENT_USER);
        renderArgs.put(CURRENT_USER, null);
    }

    @Util
    public static void loginUser(User user) {
        logger.debug("Login user : %s", user);
        session.put(CURRENT_USER, user.id);
    }
}
