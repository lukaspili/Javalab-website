package models.events;

import play.i18n.Messages;

/**
 * @author Lukasz Piliszczuk <lukasz.piliszczuk AT zenika.com>
 */
public enum ProjectState {

    PLANNIFIED, INPROGRESS, DONE, ABORTED;

    public String getLabel() {
        return Messages.get("projectState." + super.toString().toLowerCase());
    }
}
