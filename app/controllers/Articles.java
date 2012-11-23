package controllers;

import controllers.abstracts.AppController;
import controllers.security.Auth;
import controllers.security.LoggedAccess;
import controllers.security.PublicAccess;
import models.events.Article;
import models.events.Project;
import models.users.Profile;
import models.users.User;
import org.joda.time.LocalDate;
import play.Logger;
import play.libs.Codec;
import play.modules.paginate.ModelPaginator;
import service.ArticleService;
import validation.EnhancedValidator;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kevin Valfin
 */
public class Articles extends AppController {
	
	@Inject
	private static ArticleService articleService;

	
	@PublicAccess
	public static void index()  {
        ModelPaginator listArticle = new ModelPaginator(Article.class).orderBy("creationDate DESC");
        listArticle.setPageSize(5); // Nombre d'éléments par page
        listArticle.setBoundaryControlsEnabled(false); //  get/setBoundaryControlsEnabled: determines whether the First and Last buttons are displayed
		render(listArticle);
	}
	
	@LoggedAccess(Profile.MEMBER)
	public static void newArticle() {
		render();
	}
	
	@LoggedAccess(Profile.MEMBER)
	public static void create(Article article, String date) {
		User user = Auth.getCurrentUser();
		EnhancedValidator validator = validator();
        if(date != null && !date.isEmpty()) {
            String[] tab = date.split("/");
            article.creationDate = new LocalDate(Integer.valueOf(tab[2]), Integer.valueOf(tab[1]), Integer.valueOf(tab[0]));
        }
		validator.validate(article).require("content", "title", "creationDate");
		article.author = user;
		if(validator.hasErrors()) {
			flash.error("Une erreur est survenue lors de la tentative d'ajout de votre Article.");
			render("Articles/newArticle.html", article);
		}
		
		Article createdArticle = articleService.create(article);
		
		flash.success("Merci, l'article " + createdArticle.title + " à bien était enregistré");
		Articles.index();
	}
	
	@PublicAccess
    public static void details(long articleId) {
        Article article = Article.findById(articleId);
        notFoundIfNull(article);

        String randomID = Codec.UUID();
        render(article, randomID);
    }
	
}
