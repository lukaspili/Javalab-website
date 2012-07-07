package controllers;

import controllers.abstracts.AppController;
import controllers.filters.userfirstlogin.UserFirstLogin;
import controllers.security.Auth;
import controllers.security.LoggedAccess;
import controllers.security.PublicAccess;
import helper.Logger;
import models.events.Project;
import models.users.Campus;
import models.users.Picture;
import models.users.Profile;
import models.users.Promotion;
import models.users.User;
import play.Play;
import play.data.validation.Required;
import play.db.jpa.Blob;
import play.libs.OpenID;
import play.mvc.Http;
import play.mvc.Router;
import play.mvc.Util;
import service.CampusService;
import service.PictureService;
import service.ProjectService;
import service.UserService;
import util.OpenIDUtils;

import javax.inject.Inject;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    
    @Inject
    private static PictureService pictureService;
    
    @Inject
    private static ProjectService projectService;

    @Util
    public static User getSafeUser(long userId) {

        User user = User.findById(userId);
        notFoundIfNull(user);

        if (Auth.getCurrentUser().profile != Profile.GLM && !Auth.getCurrentUser().campus.equals(user.campus)) {
            forbidden();
        }

        return user;
    }

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
    public static void firstLoginProcess(User user, @Required Long campusId, Blob picture) {

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
    public static void modify(String firstName, String lastName, String presentation, File picture) {
        User user = Auth.getCurrentUser();
                       
        user.firstName = firstName;
        user.lastName = lastName;
        user.presentation = presentation;
        if(picture != null) {
        	Picture pic = pictureService.createPicture(picture);
        	user.picture = pic;
        }
        
        user.save();
        flash.success("Votre profile a été modifié !");
        profile();
    }

    @LoggedAccess
    public static void details(long userId) {

        User user = User.findById(userId);
        notFoundIfNull(user);

        List<Project> projects = projectService.getProjectsByUser(user);
        
        render(user, projects);
    }

    @LoggedAccess
    public static void profile() {
        User user = Auth.getCurrentUser();
        List<Promotion> promotionList = Arrays.asList(Promotion.values());
        render(user, promotionList);
    }
    
    @PublicAccess
	public static void member () {

    	List<User> listUser = new ArrayList<User>();
		
    	listUser = User.findAll();

		render(listUser);
    }
    @PublicAccess
	public static void candidate () {

    	render();
    }
    
    @LoggedAccess
    public static void getPicture(Long id) {
    	User user = User.findById(id);
    	notFoundIfNull(user);
    	response.setContentTypeIfNotSet(user.picture.file.type());
    	renderBinary(user.picture.file.get());
    }
}