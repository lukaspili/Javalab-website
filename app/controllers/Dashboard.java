package controllers;

import controllers.filters.security.PublicAccess;
import controllers.logicals.LogicController;

/**
 * @author Lukasz Piliszczuk <lukasz.piliszczuk AT zenika.com>
 */
@PublicAccess
public class Dashboard extends LogicController {

    public static void index() {
        render();
    }
}
