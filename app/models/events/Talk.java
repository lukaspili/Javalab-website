package models.events;

import models.users.User;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import java.util.Set;

/**
 * @author Lukasz Piliszczuk <lukasz.piliszczuk AT zenika.com>
 */
@Entity
public class Talk extends Model {

    public String title;

    public String description;

    @Type(type = "org.joda.time.contrib.hibernate.PersistentLocalDate")
    public LocalDate date;

    @Enumerated(EnumType.STRING)
    public TalkState state;

    @ManyToOne
    public User speakers;

    public Talk() {
        state = TalkState.PLANNIFIED;
    }
}
