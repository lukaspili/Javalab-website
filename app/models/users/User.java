package models.users;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;

import models.events.Article;
import models.events.Project;
import models.events.Talk;
import org.apache.commons.lang3.StringUtils;
import play.data.validation.CheckWith;
import play.data.validation.Required;
import play.db.jpa.Model;
import validation.check.NumericCheck;

/**
 * @author Lukasz Piliszczuk <lukasz.piliszczuk AT zenika.com>
 */
@Entity
public class User extends Model {

    @CheckWith(NumericCheck.class)
    public String idBooster;

    public String firstName;

    public String lastName;

    @Enumerated(EnumType.STRING)
    public Promotion promotion;

    @Enumerated(EnumType.STRING)
    public Profile profile;

    @Enumerated(EnumType.STRING)
    public Campus campus;

    @ManyToMany(mappedBy = "speakers")
    public Set<Talk> talks;

    @ManyToMany(mappedBy = "members")
    public Set<Project> projects;

    @ManyToMany(mappedBy = "authors")
    public Set<Article> articles;

    public User() {
        profile = Profile.CANDIDATE;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public boolean isTheFirstLogin() {
        return null == id;
    }


}
