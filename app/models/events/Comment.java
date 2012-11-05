package models.events;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import play.db.jpa.Model;

import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: AkeT
 * Date: 11/4/12
 * Time: 7:27 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Comment extends Model {

    public String username;

    public String content;

    @Type(type = "org.joda.time.contrib.hibernate.PersistentLocalDate")
    public LocalDate date;

    public Comment() {
        this.date = new LocalDate();
    }

}
