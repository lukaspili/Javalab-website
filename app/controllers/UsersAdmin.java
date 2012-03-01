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
