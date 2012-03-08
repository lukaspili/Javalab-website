package models.events;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import models.users.Campus;
import models.users.Picture;
import models.users.User;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import play.db.jpa.Model;

/**
 * @author Lukasz Piliszczuk <lukasz.piliszczuk AT zenika.com>
 */
@Entity
public class Project extends Model {

    public String name;

    public String presentation;
    
    public String technologies;
    
    public String description;

    @Type(type = "org.joda.time.contrib.hibernate.PersistentLocalDate")
    public LocalDate creationDate;

    @Enumerated(EnumType.STRING)
    public ProjectState state;
    
    @OneToMany
    public Set<Picture> pictures;
    
    @ManyToOne
    public Campus campus;

    @ManyToMany
    public Set<User> members;

    public Project() {
        creationDate = new LocalDate();
        state = ProjectState.PLANNIFIED;
    }
}
