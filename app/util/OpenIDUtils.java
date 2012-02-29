package util;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Lukasz Piliszczuk <lukasz.pili AT gmail.com>
 */
public class OpenIDUtils {

    public static String getParamValue(String param, int position) {

        if (StringUtils.isBlank(param)) {
            return null;
        }

        String[] values = param.split(";");

        if (position > values.length) {
            return null;
        }

        return values[position];
    }
}
