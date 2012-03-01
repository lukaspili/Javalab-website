package controllers;

import controllers.abstracts.AppController;
import controllers.security.Auth;
import controllers.security.LoggedAccess;
import models.users.Campus;
import models.users.Profile;
import models.users.User;
import service.UserService;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Lukasz Piliszczuk <lukasz.piliszczuk AT zenika.com>
 */
public class UsersAdmin extends AppController {

    @Inject
    private static UserService userService;

    /**
     * List of candidates of a specified campus
     * Only a clm/glm can access to this information
     * <p/>
     * GLM need to specify the campus name
     * CLM can only see candidates from his own campus
     */
    @LoggedAccess(Profile.CLM)
    public static void candidates(String campusName) {
        Campus campus = null;
        if (Auth.getCurrentUser().profile == Profile.GLM) {
            try {
//                campus = Campus.valueOf(campusName.toUpperCase());
            } catch (IllegalArgumentException e) {
                notFound();
            }
        } else {
            campus = Auth.getCurrentUser().campus;
        }
        List<User> candidates = userService.getCandidatesByCampus(campus);
        render(candidates);
    }

    @LoggedAccess(Profile.CLM)
    public static void validate(String idBooster, String validation) {
        if (idBooster != null && !idBooster.isEmpty()) {
            if (validation != null && !validation.isEmpty()) {
                User user = User.find("byIdBooster", idBooster).first();
                if (validation.equals("accept")) {
                    user.profile = Profile.MEMBER;
                    user.save();
                    flash.success("La candidature de l'étudiant " + user.getFullName() + " a bien été validée");
                }
                if (validation.equals("refuse")) {
                    flash.success("La candidature de l'étudiant " + user.getFullName() + " a bien été refusée");
                }
            } else {
                flash.error("Vous devez accepter ou refuser la candidature avant de valider");
            }
        } else {
            flash.error("Validation impossible: il semble que l'étudiant séléctionné ne possède pas d'ID Booster");
        }
        UsersAdmin.candidates(null);
    }

    @LoggedAccess(Profile.CLM)
    public static void members(String campusName) {
        Campus campus = null;
        if (Auth.getCurrentUser().profile == Profile.GLM) {
            try {
//                campus = Campus.valueOf(campusName.toUpperCase());
            } catch (IllegalArgumentException e) {
                notFound();
            }
        } else {
            campus = Auth.getCurrentUser().campus;
        }
        List<User> members = userService.getMembersByCampus(campus);
        render(members);
    }

}
