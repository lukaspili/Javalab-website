package models.users;

import models.events.Project;
import play.db.jpa.Model;
import play.i18n.Messages;

import javax.persistence.*;
import java.util.List;

/**
 * @author Lukasz Piliszczuk <lukasz.piliszczuk AT zenika.com>
 */
@Entity
public class Campus extends Model {

    @Enumerated(EnumType.STRING)
    public Name name;

    @OneToMany(mappedBy = "campus")
    public List<User> members;

    @OneToMany(mappedBy = "campus")
    public List<Project> projects;

    public enum Name {
        PARIS, CLERMONTFERRAND, NICE, NANTES, TOULOUSE, MONTPELLIER, GRENOBLE;

        public String getLabel() {
            return Messages.get("campus." + super.toString().toLowerCase());
        }
    }
}
