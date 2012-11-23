package controllers;

import controllers.abstracts.AppController;
import controllers.security.Auth;
import controllers.security.LoggedAccess;
import controllers.security.PublicAccess;
import models.events.Talk;
import models.users.Profile;
import models.users.User;
import org.joda.time.LocalDate;
import play.modules.paginate.ModelPaginator;
import service.UserService;
import validation.EnhancedValidator;

import javax.inject.Inject;
import javax.swing.text.TabableView;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: AkeT
 * Date: 11/5/12
 * Time: 4:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class Talks extends AppController {

    @Inject
    private static UserService userService;

    @PublicAccess
    public static void index() {
        ModelPaginator talks = new ModelPaginator(Talk.class).orderBy("date DESC");
        talks.setPageSize(9); // Nombre d'éléments par page
        talks.setBoundaryControlsEnabled(false); //  get/setBoundaryControlsEnabled: determines whether the First and Last buttons are displayed
        render(talks);
    }

    @LoggedAccess(Profile.CLM)
    public static void create() {
        User user = Auth.getCurrentUser();
        List<User> users = userService.getCandidatesByCampus(user.campus);
        render(users);
    }

    @LoggedAccess(Profile.CLM)
    public static void add(Talk talk, String date) {
        List<User> users = userService.getCandidatesByCampus(Auth.getCurrentUser().campus);

        talk.speaker = User.findById(Long.valueOf(params.get("speakerId")));

        if(date != null && !date.isEmpty()) {
            String[] tab = date.split("/");
            talk.date = new LocalDate(Integer.valueOf(tab[2]),Integer.valueOf(tab[1]),Integer.valueOf(tab[0]));
        }
        EnhancedValidator validator = validator();
        validator.validate(talk).require("title", "speaker","content", "iframe", "date");
        if(validator.hasErrors()) {
            flash.error("Une erreur est survenue lors de la tentative d'ajout de votre Mini-Talk.");
            render("Talks/create.html", talk, users);
        }
        talk.save();
        index();
    }

    @PublicAccess
    public static void details(long talkId) {
        Talk talk = Talk.findById(talkId);
        notFoundIfNull(talk);
        render(talk);
    }
}
