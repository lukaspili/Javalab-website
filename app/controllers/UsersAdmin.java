package controllers;

import controllers.filters.AuthFilter;
import controllers.filters.security.LoggedAccess;
import controllers.logicals.LogicController;
import models.users.Campus;
import models.users.Profile;
import models.users.User;
import service.UserService;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Lukasz Piliszczuk <lukasz.piliszczuk AT zenika.com>
 */
public class UsersAdmin extends LogicController {

    @Inject
    private static UserService userService;

    /**
     * List of candidates of a specified campus
     * Only a clm/glm can access to this information
     *
     * GLM need to specify the campus name
     * CLM can only see candidates from his own campus
     */
    @LoggedAccess(Profile.CLM)
    public static void candidates(String campusName) {

        Campus campus = null;

        if (AuthFilter.getCurrentUser().profile == Profile.GLM) {

            try {
                campus = Campus.valueOf(campusName.toUpperCase());
            } catch (IllegalArgumentException e) {
                notFound();
            }

        } else {
            campus = AuthFilter.getCurrentUser().campus;
        }

        List<User> candidates = userService.getCandidatesByCampus(campus);

        render(candidates);
    }

}
