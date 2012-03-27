package controllers.filters;

import controllers.abstracts.UtilController;
import play.Logger;
import play.mvc.After;
import play.mvc.Before;

/**
 * @author Lukasz Piliszczuk <lukasz.pili AT gmail.com>
 */
public class ApplicationFilter extends UtilController {

    @Before
    public static void templateMenu() {
        setMenuDefault();
    }
}
