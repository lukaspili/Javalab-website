package models.users;

import javax.persistence.Entity;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import play.db.jpa.Blob;
import play.db.jpa.Model;

@Entity
public class Picture extends Model {

	public String name;
	
	public Blob file;
	
	public boolean principal;
	
	@Type(type = "org.joda.time.contrib.hibernate.PersistentLocalDate")
	public LocalDate date;
	
	public Picture() {
		date = new LocalDate();
		principal = Boolean.FALSE;
	}
	
}
