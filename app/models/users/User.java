package models.users;

import models.events.News;
import models.events.Project;
import models.events.Talk;
import play.data.validation.CheckWith;
import play.db.jpa.Model;
import validation.check.NumericCheck;

import javax.persistence.*;
import java.util.Set;

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
    public Set<News> news;

    public User() {
        profile = Profile.CANDIDATE;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
