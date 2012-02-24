package helper;

import exceptions.CoreException;
import org.apache.commons.lang3.StringUtils;
import play.Play;

/**
 * @author Lukasz Piliszczuk <lukasz.pili AT gmail.com>
 */
public final class ApplicationConfigHelper {

    private ApplicationConfigHelper() {
    }

    public static Id getCurrentId() {

        String id = Play.id;

        if (StringUtils.isEmpty(id)) {
            id = Id.DEV.toString();
        }

        Id currentId = Id.valueOf(id);

        if (currentId.equals(Id.DEV)) {
            return Id.DEV;
        } else if (currentId.equals(Id.PROD)) {
            return Id.PROD;
        }

        throw new CoreException("Unknown play id : " + Play.id);
    }

    public enum Id {
        DEV, PROD
    }

}
