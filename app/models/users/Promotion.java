package models.users;

import play.i18n.Messages;

/**
 * @author Lukasz Piliszczuk <lukasz.piliszczuk AT zenika.com>
 */
public enum Promotion {

    B1, B2, B3, M1, M2;

    public String getLabel() {
        return super.toString();
    }
}
