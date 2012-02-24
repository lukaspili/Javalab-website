package helper;

import controllers.abstracts.AppController;
import controllers.abstracts.UtilController;

/**
 * @author Lukasz Piliszczuk <lukasz.pili AT gmail.com>
 */
public final class TagHelper {

    public static String getTagFor(Class clazz) {

        String tag = clazz.getSimpleName();

        if (clazz.getSuperclass().equals(AppController.class) || clazz.getSuperclass().equals(UtilController.class)) {
            tag += "Controller";
        }

        return tag += " : ";
    }
}
