package models.events;

import models.users.User;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

/**
 * @author Valfin Kevin
 *
 */
@Entity
public class Article  extends Model {
	
	   public String title;

	    public String content;

	    @Type(type = "org.joda.time.contrib.hibernate.PersistentLocalDate")
	    public LocalDate creationDate;

	    @ManyToMany
	    public Set<User> authors;

	    public Article() {
	        creationDate = new LocalDate();
	    }
	}