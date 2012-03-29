package models.users;

import java.util.Set;

import javax.persistence.*;

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

    public String presentation;

    public boolean firstLogin;

    @Enumerated(EnumType.STRING)
    public Promotion promotion;

    @Enumerated(EnumType.STRING)
    public Profile profile;

    @OneToOne
    public Picture picture;

    @ManyToOne
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

    @Override
    public String toString() {
        return "User " + idBooster + " " + getFullName();
    }
}
