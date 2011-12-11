package controllers.functionnals;

import controllers.helpers.controller.CollectionHelper;
import controllers.helpers.controller.PageHelper;
import play.i18n.Messages;
import play.mvc.Controller;
import validation.EnhancedValidator;

/**
 * @author Lukasz Piliszczuk <lukasz.piliszczuk AT zenika.com>
 */

public abstract class FunctionnalController extends Controller {

    protected static CollectionHelper collectionHelper = new CollectionHelper();

    protected static EnhancedValidator validator() {
        return new EnhancedValidator(validation, params);
    }

    protected static PageHelper pageHelper(String page) {
        return new PageHelper(page, renderArgs);
    }

    protected static void flashInfo(String message, Object... args) {
        flashInfo(message, true, args);
    }

    protected static void flashInfoSamePage(String message, Object... args) {
        flashInfo(message, false, args);
    }

    protected static void flashInfo(String message, boolean keep, Object... args) {

        for (int i = 1; i <= 5; i++) {
            String box = "info_box_" + i;

            if (!flash.contains(box)) {
                flash.put(box, Messages.get(message, args));

                if (!keep) {
                    flash.discard(box);
                }

                break;
            }
        }
    }

    protected static void flashError(String message, Object... args) {
        flashError(message, true, args);
    }

    protected static void flashErrorSamePage(String message, Object... args) {
        flashError(message, false, args);
    }

    protected static void flashError(String message, boolean keep, Object... args) {

        for (int i = 1; i <= 5; i++) {
            String box = "error_box_" + i;

            if (!flash.contains(box)) {
                flash.put(box, Messages.get(message, args));

                if (!keep) {
                    flash.discard(box);
                }

                break;
            }
        }
    }

    protected static void flashSuccess(String message, Object... args) {
        flash.put("success_box", Messages.get(message, args));
    }
}
