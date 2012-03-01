package controllers;

import controllers.abstracts.AppController;
import controllers.security.Auth;
import controllers.security.LoggedAccess;
import models.users.Campus;
import models.users.Profile;

/**
 * @author Lukasz Piliszczuk <lukasz.pili AT gmail.com>
 */
public class Campuses extends AppController {

    public static void index() {
        render();
    }
}
