package models.events;

import play.i18n.Messages;

/**
 * @author Lukasz Piliszczuk <lukasz.piliszczuk AT zenika.com>
 */
public enum TalkState {

    PLANNIFIED, DONE, ABORTED;

    public String getLabel() {
        return Messages.get("talkState." + super.toString().toLowerCase());
    }
}
