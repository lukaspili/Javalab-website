package controllers.abstracts;

import controllers.filters.userfirstlogin.UserFirstLoginFilter;
import controllers.security.Auth;
import play.mvc.With;

/**
 * @author Lukasz Piliszczuk <lukasz.piliszczuk AT zenika.com>
 */
@With({Auth.class, UserFirstLoginFilter.class})
public abstract class AppController extends UtilController {

}
