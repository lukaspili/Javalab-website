package controllers;

import controllers.abstracts.AppController;
import controllers.security.Auth;
import controllers.security.LoggedAccess;
import controllers.security.PublicAccess;
import models.events.Project;
import models.users.Profile;
import models.users.User;
import service.ProjectService;
import sun.net.httpserver.AuthFilter;
import validation.EnhancedValidator;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Kevin Valfin
 */

public class Projects extends AppController {

    @Inject
    private static ProjectService projectService;

    @PublicAccess
    public static void index() {
        pageHelper().addActionTitle();
        setMenuAsController();
        
        User user = Auth.getCurrentUser();
        if(user != null && user.isMember()) {
        	List<Project> projects = projectService.getProjectsByCampus(
                Auth.getCurrentUser().campus);
        	render(projects, user);
        }else {
        	render();
        }
    }

    @LoggedAccess(Profile.CLM)
    public static void createProject() {
        render();
    }

    @LoggedAccess(Profile.CLM)
    public static void addProject(Project project) {
        EnhancedValidator validator = validator();
        validator.validate(project).require("name", "description", "technologies", "presentation");
        if (validator.hasErrors()) {
            render("UsersAdmin/createProject", project);
        }
        project.campus = Auth.getCurrentUser().campus;
        Project createdProject = projectService.createProject(project);
        flash.success("Le projet " + createdProject.name + " a bien été crée.");
        Projects.index();
    }

    @LoggedAccess(Profile.MEMBER)
    public static void susbscribe(Long id) {
        Project project = Project.findById(id);
        User currentUser = Auth.getCurrentUser();
        project.members.add(currentUser);
        project.save();
        flash.success("Votre inscription au projet "+project.name+" a bien été prise en compte.");
        Projects.index();
    }

    @LoggedAccess(Profile.MEMBER)
    public static void details(Long id) {
        Project project = Project.findById(id);
        render(project);
    }
}
