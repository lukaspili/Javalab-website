package models.users;

import play.i18n.Messages;

/**
 * @author Lukasz Piliszczuk <lukasz.piliszczuk AT zenika.com>
 */
public enum Campus {

    PARIS, CLERMONTFERRAND, NICE, NANTES, TOULOUSE, MONTPELLIER, GRENOBLE;

    public String getLabel() {
        return Messages.get("campus." + super.toString().toLowerCase());
    }
}
