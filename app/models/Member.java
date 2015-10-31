package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.data.format.Formats.NonEmpty;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

import com.avaje.ebean.validation.Email;

@Entity
@Table(name="mygift_user")
public class Member extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	public Long id;
	
	@Required
	@NonEmpty
	public String login;
	
	@Required
	@MinLength(7)
	public String password;
	
	@Required
	@Email
	public String email;
	
	public long lastConnexion;
	public boolean isAdmin;
	
	public static Finder<Long,Member> find = new Finder<Long, Member>(
			Long.class, Member.class
	);
	
	/**
     * Retrieve all users.
     */
    public static List<Member> findAll() {
        return find.all();
    }

    /**
     * Retrieve a User from email.
     */
    public static Member findByEmail(String email) {
        return find.where().eq("email", email).findUnique();
    }
    
    /**
     * Authenticate a User.
     */
    public static Member authenticate(String email, String password) {
        return find.where()
            .eq("email", email)
            .eq("password", password)
            .findUnique();
    }
    
    // --
    
    public String toString() {
        return "login (" + email + ")";
    }
	
	public static void create(Member member) {
		member.save();
	}
	
	public static void delete(Long id) {
		find.ref(id).delete();
	}
}
