package controllers.logicals;

import controllers.filters.AuthFilter;
import controllers.filters.GlobalFilter;
import controllers.functionnals.FunctionnalController;
import controllers.helpers.view.AuthViewHelper;
import play.mvc.With;

/**
 * @author Lukasz Piliszczuk <lukasz.piliszczuk AT zenika.com>
 */
@With({GlobalFilter.class, AuthFilter.class, AuthViewHelper.class})
public abstract class LogicController extends FunctionnalController {

}
