package helper;

import controllers.abstracts.AppController;
import controllers.abstracts.UtilController;

/**
 * @author Lukasz Piliszczuk <lukasz.pili AT gmail.com>
 */
public class Logger {

    private String tag;

    private Logger(String tag) {
        this.tag = tag;
    }

    public static Logger getLoggerFor(Class clazz) {

        String tag = clazz.getSimpleName();

        if (clazz.getSuperclass().equals(AppController.class) || clazz.getSuperclass().equals(UtilController.class)) {
            tag += "Controller";
        }

        tag += " : ";

        return new Logger(tag);
    }

    public void debug(String messsage, Object... args) {
        play.Logger.debug(tag + messsage, args);
    }

    public void debug(Throwable e, String messsage, Object... args) {
        play.Logger.debug(e, tag + messsage, args);
    }

    public void warn(String messsage, Object... args) {
        play.Logger.warn(tag + messsage, args);
    }

    public void warn(Throwable e, String messsage, Object... args) {
        play.Logger.warn(e, tag + messsage, args);
    }

    public void error(String messsage, Object... args) {
        play.Logger.error(tag + messsage, args);
    }

    public void error(Throwable e, String messsage, Object... args) {
        play.Logger.error(e, tag + messsage, args);
    }
}
