package models.events;

import models.users.User;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import java.util.List;
import java.util.Set;

/**
 * @author Valfin Kevin
 *
 */
@Entity
public class Article  extends Model {
	
    public String title;

    @Lob
    public String content;

    @Type(type = "org.joda.time.contrib.hibernate.PersistentLocalDate")
    public LocalDate creationDate;

    @ManyToOne
    public User author;

    @OneToMany
    public List<Comment> comments;

    public Article() {
        creationDate = new LocalDate();
    }

    public String getLimitedContent() {
        int limit = 550;
        if(content.length() > limit) {
            return content.substring(0, limit)+"...";
        }
        return  content;
    }

    public String getFormattedCreationDate() {
        return creationDate.toString("dd/MM/yy");
    }
	
}