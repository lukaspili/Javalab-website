package models.events;

import models.users.User;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import play.db.jpa.Model;
import javax.persistence.Lob;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

/**
 * @author Lukasz Piliszczuk <lukasz.piliszczuk AT zenika.com>
 */
@Entity
public class Talk extends Model {

    public String title;

    public String iframe;

    @Lob
	public String content;

    @Type(type = "org.joda.time.contrib.hibernate.PersistentLocalDate")
    public LocalDate date;

    @ManyToOne
    public User speaker;

    public Talk() {}
}
