package models.users;

import play.i18n.Messages;

/**
 * @author Lukasz Piliszczuk <lukasz.piliszczuk AT zenika.com>
 */
public enum Profile {

    CANDIDATE, MEMBER, CLM, GLM;

    public String getLabel() {
        return Messages.get("profile." + super.toString().toLowerCase());
    }

    public boolean hasAccessOn(Profile profile) {

        if (this == GLM) {
            return true;
        } else if (this == CLM && profile != GLM) {
            return true;
        } else if (this == MEMBER && profile != GLM && profile != CLM) {
            return true;
        } else if (this == CANDIDATE && profile == CANDIDATE) {
            return true;
        }

        return false;
    }
}
