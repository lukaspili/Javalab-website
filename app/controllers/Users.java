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
import service.CampusService;
import service.UserService;
import util.OpenIDUtils;

import javax.inject.Inject;
import java.util.*;

/**
 * @author Lukasz Piliszczuk <lukasz.piliszczuk AT zenika.com>
 */
public class Users extends AppController {

    private static final Logger logger = Logger.getLoggerFor(Users.class);

    private static final String OPENID_PERSONCAMPUS = "openid.alias3.value.alias2";
    private static final String OPENID_PERSONCURSUS = "openid.alias3.value.alias4";

    @Inject
    private static UserService userService;

    @Inject
    private static CampusService campusService;

    @PublicAccess(only = true)
    public static void login() {
        pageHelper().addActionTitle();
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

            if (null == user) {

                logger.debug("User dosen't exists in database, create new one with idbooster and redirect to first login");

                user = new User();
                user.idBooster = idBooster;

                String campus = OpenIDUtils.getParamValue(params.get(OPENID_PERSONCAMPUS), 2);
                String promotion = OpenIDUtils.getParamValue(params.get(OPENID_PERSONCURSUS), 2);

                try {
                    user.campus = campusService.findCampusByName(campus);
                } catch (Exception e) {
                    logger.warn("Invalid campus value : %s", campus);
                }

                try {
                    user.promotion = Promotion.valueOf(Integer.valueOf(promotion));
                } catch (Exception e) {
                    logger.warn("Invalid promotion level : %s", promotion);
                }

                user = userService.save(user);

                if (null == user) {
                    Dashboard.index();
                }
            }

            Auth.loginUser(user);

            if (user.firstLogin) {
                firstLogin();
            }

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
    @UserFirstLogin(only = true)
    public static void firstLogin() {

        pageHelper().addActionTitle();

        User user = Auth.getCurrentUser();
        List<Campus> campuses = campusService.getCampuses();
        List<Promotion> promotions = Arrays.asList(Promotion.values());

        render(user, campuses, promotions);
    }

    @LoggedAccess
    @UserFirstLogin(only = true)
    public static void firstLoginProcess(User user, @Required Long campusId) {

        if (validator().validate(user).require("firstName", "lastName", "promotion").hasErrors()) {

            List<Campus> campuses = campusService.getCampuses();
            List<Promotion> promotions = Arrays.asList(Promotion.values());

            render("Users/firstLogin.html", user, campuses, promotions, campusId);
        }

        user.campus = Campus.findById(campusId);
        notFoundIfNull(user.campus);

        userService.completeFirstLogin(Auth.getCurrentUser(), user);

        flashSuccess("user.firstLoginProcess.success");

        Dashboard.index();
    }

    @LoggedAccess
    @UserFirstLogin
    public static void logout() {
        Auth.logoutUser();
        Dashboard.index();
    }

    @LoggedAccess
    public static void candidature() {

    }


    @LoggedAccess
    public static void modify(String firstName, String lastName, Campus campus) {
        User user = Auth.getCurrentUser();
        user.firstName = firstName;
        user.lastName = lastName;
        user.campus = campus;
        user.save();
        flash.success("Votre profile a été modifié !");
        profile();
    }

    @LoggedAccess
    public static void details(String idBooster) {
        User user = User.find("byIdBooster", idBooster).first();
        render(user);
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
        List<Promotion> promotionList = Arrays.asList(Promotion.values());
        render(user, promotionList);
    }

    @LoggedAccess
    public static void candidate() {
        User user = Auth.getCurrentUser();
        user.profile = Profile.CANDIDATE;
        user.save();
        render(user);
    }
}