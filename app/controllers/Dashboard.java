package controllers;

import controllers.abstracts.AppController;
import controllers.security.PublicAccess;

/**
 * @author Lukasz Piliszczuk <lukasz.piliszczuk AT zenika.com>
 */
@PublicAccess
public class Dashboard extends AppController {

    public static void index() {

        pageHelper().addActionTitle();

        render();
    }
    
    public static void agenda() {

        render();
    }
}
