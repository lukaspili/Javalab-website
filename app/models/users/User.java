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

	public String getIdBooster() {
		return idBooster;
	}

	public void setIdBooster(String idBooster) {
		this.idBooster = idBooster;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Campus getCampus() {
		return campus;
	}

	public void setCampus(Campus campus) {
		this.campus = campus;
	}

	public Set<Talk> getTalks() {
		return talks;
	}

	public void setTalks(Set<Talk> talks) {
		this.talks = talks;
	}

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	public Set<News> getNews() {
		return news;
	}

	public void setNews(Set<News> news) {
		this.news = news;
	}

	public User(String idBooster, String firstName, String lastName,
			Promotion promotion, Profile profile, Campus campus,
			Set<Talk> talks, Set<Project> projects, Set<News> news) {
		super();
		this.idBooster = idBooster;
		this.firstName = firstName;
		this.lastName = lastName;
		this.promotion = promotion;
		this.profile = profile;
		this.campus = campus;
		this.talks = talks;
		this.projects = projects;
		this.news = news;
	}
    
    
}
