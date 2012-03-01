package controllers;

import controllers.abstracts.AppController;
import controllers.security.LoggedAccess;
import models.users.Campus;
import models.users.Profile;
import service.CampusService;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Lukasz Piliszczuk <lukasz.pili AT gmail.com>
 */
@LoggedAccess(Profile.GLM)
public class Glm extends AppController {

    @Inject
    private static CampusService campusService;

    public static void index() {
        List<Campus> campuses = campusService.getCampuses();
        render(campuses);
    }
}
