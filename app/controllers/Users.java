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
                    user.campus = Campus.valueOf(campus);
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
        List<Campus> campuses = Arrays.asList(Campus.values());
        List<Promotion> promotions = Arrays.asList(Promotion.values());

        render(user, campuses, promotions);
    }

    @LoggedAccess
    @UserFirstLogin(only = true)
    public static void firstLoginProcess(User user) {

        if (validator().validate(user).require("firstName", "lastName", "campus", "promotion").hasErrors()) {

            List<Campus> campuses = Arrays.asList(Campus.values());
            List<Promotion> promotions = Arrays.asList(Promotion.values());

            render("Users/firstLogin.html", user, campuses, promotions);
        }

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

//    @PublicAccess(only = true)
//    public static void create(User user) {
//        EnhancedValidator validator = validator();
//        validator.validate(user).require("idBooster", "firstName", "lastName", "promotion", "campus");
//        if (validator.hasErrors()) {
//            render("Users/inscription.html", user);
//        }
//        User createdUser = userService.save(user);
//        flash.success("Merci " + createdUser.firstName + createdUser.lastName + ", vous êtes bien enregistré");
//        Dashboard.index();
//    }

    @LoggedAccess
    public static void candidate() {
        User user = Auth.getCurrentUser();
        user.profile = Profile.CANDIDATE;
        user.save();
        render(user);
    }


}


//http://www.suparis.fr/supinfoOpenIdPost.php?openid.claimed_id=https%3A%2F%2Fid.supinfo.com%2Fme%2F75054&openid.identity=https%3A%2F%2Fid.supinfo.com%2Fme%2F75054&openid.sig=IPxQNJWjhRJfaEaDcVhGtnhOUYGooC%2BGxlXlOBdWIvI%3D&openid
// .signed=claimed_id%2Cidentity%2Cassoc_handle%2Cop_endpoint%2Creturn_to%2Cresponse_nonce%2Cns.alias3%2Calias3.mode%2Calias3.type.alias1%2Calias3.value.alias1%2Calias3.type.alias2%2Calias3.value.alias2%2Calias3.type.alias3%2Calias3.value.alias3%2Calias3.type.alias4%2Calias3.value.alias4%2Calias3.type.alias5%2Calias3.count.alias5%2Calias3.value.alias5.1%2Calias3.value.alias5.2%2Calias3.type.alias6%2Calias3.count.alias6%2Calias3.type.alias7%2Calias3.count.alias7%2Calias3.value.alias7.1%2Calias3.value.alias7.2%2Calias3.value.alias7.3%2Calias3.value.alias7.4%2Calias3.value.alias7.5&openid.assoc_handle=%7B634659297944867132%7D%7BSPwXyQ%3D%3D%7D%7B32%7D&openid.op_endpoint=https%3A%2F%2Fid.supinfo.com%2Fserver.aspx&openid.return_to=http%3A%2F%2Fwww.suparis.fr%2FsupinfoOpenIdPost.php&openid.response_nonce=2012-02-27T11%3A09%3A58ZcvXXcpCA&openid.mode=id_res&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&openid.ns.alias3=http%3A%2F%2Fopenid.net%2Fsrv%2Fax%2F1.0&openid.alias3.mode=fetch_response&openid.alias3.type.alias1=PersonRole&openid.alias3.value.alias1=Student&openid.alias3.type.alias2=PersonCampus&openid.alias3.value.alias2=305%3BSUPINFO%20Paris%3B&openid.alias3.type.alias3=PersonClasse&openid.alias3.value.alias3=A&openid.alias3.type.alias4=PersonCursus&openid.alias3.value.alias4=SIIT%3B4%3BWorldWide%3B&openid.alias3.type.alias5=PersonRank&openid.alias3.count.alias5=2&openid.alias3.value.alias5.1=Discover&openid.alias3.value.alias5.2=Advanced&openid.alias3.type.alias6=FullProfSubjects&openid.alias3.count.alias6=0&openid.alias3.type.alias7=TeacherSubjects&openid.alias3.count.alias7=5&openid.alias3.value.alias7.1=JAVA&openid.alias3.value.alias7.2=ORCL&openid.alias3.value.alias7.3=AAPL&openid.alias3.value.alias7.4=WWWT&openid.alias3.value.alias7.5=IMGT