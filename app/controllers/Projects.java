package controllers;

import controllers.abstracts.AppController;
import controllers.security.Auth;
import controllers.security.LoggedAccess;
import models.events.Project;
import models.users.Profile;
import models.users.User;
import service.ProjectService;
import validation.EnhancedValidator;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Kevin Valfin
 */

public class Projects extends AppController {

    @Inject
    private static ProjectService projectService;

    @LoggedAccess(Profile.CLM)
    public static void createProject() {
        render();
    }

    @LoggedAccess(Profile.CLM)
    public static void previsualise(Project project) {
        Project clearProject = new Project();
//        MarkdownProcessor processor = new MarkdownProcessor();
//        clearProject.presentation = processor.markdown(project.presentation);
//        clearProject.technologies = processor.markdown(project.technologies);
//        clearProject.description = processor.markdown(project.description);
        render(clearProject);
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
        Projects.createProject();
    }

    @LoggedAccess(Profile.MEMBER)
    public static void projects() {
        List<Project> projects = projectService.getProjectsByCampus(
                Auth.getCurrentUser().campus);
        render(projects);
    }

    @LoggedAccess(Profile.MEMBER)
    public static void susbscribe(Long id) {
        Project project = Project.findById(id);
        User currentUser = Auth.getCurrentUser();
        project.members.add(currentUser);
        project.save();
        Projects.projects();
    }

    @LoggedAccess(Profile.MEMBER)
    public static void details(Long id) {
        Project project = Project.findById(id);
        render(project);
    }
}
