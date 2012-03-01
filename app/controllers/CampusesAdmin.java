package controllers;

import controllers.abstracts.AppController;
import controllers.security.Auth;
import controllers.security.LoggedAccess;
import models.users.Campus;
import models.users.Profile;

/**
 * @author Lukasz Piliszczuk <lukasz.pili AT gmail.com>
 */
@LoggedAccess(Profile.CLM)
public class CampusesAdmin extends AppController {

    public static void admin(long campusId) {

        Campus campus = Campus.findById(campusId);
        notFoundIfNull(campus);

        if (Auth.getCurrentUser().profile != Profile.GLM && !Auth.getCurrentUser().campus.equals(campus)) {
            forbidden();
        }

        pageHelper().addActionTitle(campus.name.getLabel());

        render(campus);
    }
}
