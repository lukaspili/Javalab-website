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
	    
	    public Article() {
	        creationDate = new LocalDate();
	    }
	
}