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

    @Required
    @CheckWith(NumericCheck.class)
    public String idBooster;

    @Required
    public String firstName;

    @Required
    public String lastName;

    @Required
    @Enumerated(EnumType.STRING)
    public Promotion promotion;

    @Required
    @Enumerated(EnumType.STRING)
    public Profile profile;

    @Required
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
        return StringUtils.isEmpty(firstName);
    }


}
