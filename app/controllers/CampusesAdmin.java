package controllers;

import controllers.abstracts.AppController;
import controllers.security.Auth;
import controllers.security.LoggedAccess;
import models.users.Campus;
import models.users.Profile;
import models.users.User;
import play.data.validation.Required;
import play.mvc.Util;
import service.CampusService;
import service.UserService;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Lukasz Piliszczuk <lukasz.pili AT gmail.com>
 */
@LoggedAccess(Profile.CLM)
public class CampusesAdmin extends AppController {

    @Inject
    private static CampusService campusService;

    @Inject
    private static UserService userService;

    @Util
    public static Campus getSafeCampus(long campusId) {

        Campus campus = Campus.findById(campusId);
        notFoundIfNull(campus);

        if (Auth.getCurrentUser().profile != Profile.GLM && !Auth.getCurrentUser().campus.equals(campus)) {
            forbidden();
        }

        return campus;
    }

    public static void admin(long campusId) {

        Campus campus = getSafeCampus(campusId);

        pageHelper().addActionTitle(campus.name.getLabel());

        List<User> members = userService.getUsersByCampusAndProfile(campus, Profile.MEMBER);
        List<User> candidates = userService.getUsersByCampusAndProfile(campus, Profile.CANDIDATE);

        render(campus, members, candidates);
    }

    /**
     * List of candidates of a specified campus
     * Only a clm/glm can access to this information
     * <p/>
     * GLM need to specify the campus name
     * CLM can only see candidates from his own campus
     */
    @LoggedAccess(Profile.CLM)
    public static void candidates(long campusId) {

        Campus campus = getSafeCampus(campusId);

        List<User> candidates = userService.getUsersByCampusAndProfile(campus, Profile.CANDIDATE);

        render(campus, candidates);
    }

    @LoggedAccess(Profile.CLM)
    public static void validate(@Required Long id, String refused, String accepted) {

        User user = Users.getSafeUser(id);

        if (null != accepted) {
            userService.acceptCandidate(user);
        } else if (null != refused) {
            userService.refuseCandidate(user);
        } else {
            notFound();
        }

        List<User> candidates = userService.getUsersByCampusAndProfile(user.campus, Profile.CANDIDATE);

        if (!candidates.isEmpty()) {
            candidates(user.campus.id);
        }

        admin(user.campus.id);
    }

    /**
     * List of candidates of a specified campus
     * Only a clm/glm can access to this information
     * <p/>
     * GLM need to specify the campus name
     * CLM can only see candidates from his own campus
     */
    @LoggedAccess(Profile.CLM)
    public static void members(long campusId) {

        Campus campus = getSafeCampus(campusId);

        List<User> members = userService.getUsersByCampusAndProfile(campus, Profile.MEMBER);

        render(campus, members);
    }
}
