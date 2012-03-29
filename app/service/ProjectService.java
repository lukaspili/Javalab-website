package service;

import models.events.Project;
import models.events.ProjectState;
import models.users.Campus;
import models.users.User;

import org.joda.time.LocalDate;

import controllers.Projects;

import javax.persistence.Query;
import java.util.List;

/**
 * @author Kevin Valfin
 */
public class ProjectService {
	
	
	public Project createProject(Project project) {
		Project projectToCreate = new Project();
		projectToCreate.name = project.name;
		projectToCreate.presentation = project.presentation;
		projectToCreate.technologies = project.technologies;
		projectToCreate.description = project.description;
		projectToCreate.creationDate = new LocalDate();
		projectToCreate.state = ProjectState.PLANNIFIED;
		projectToCreate.campus = project.campus;
		
		projectToCreate.save();
		
		return projectToCreate;
	}
	
	public List<Project> getProjectsByCampus(Campus campus) {

        Query query = Project.em().createQuery("select p FROM Project p " +
                "where p.campus = :campus");

        query.setParameter("campus", campus);

        return query.getResultList();
	}
	
	public List<Project> getProjectsByUser(User user) {
		Query query = Project.em().createQuery("SELECT p FROM Project p " +
				"WHERE :user MEMBER OF p.members");
		
		query.setParameter("user", user);
		
		return query.getResultList();
	}

}
