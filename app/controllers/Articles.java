package controllers;

import controllers.abstracts.AppController;
import controllers.security.Auth;
import controllers.security.LoggedAccess;
import controllers.security.PublicAccess;
import models.events.Article;
import models.events.Project;
import models.users.Profile;
import models.users.User;
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
        ModelPaginator listArticle = new ModelPaginator(Article.class);
        listArticle.setPageSize(5); // Nombre d'éléments par page
        listArticle.setBoundaryControlsEnabled(false); //  get/setBoundaryControlsEnabled: determines whether the First and Last buttons are displayed
		render(listArticle);
	}
	
	@LoggedAccess(Profile.MEMBER)
	public static void newArticle() {
		render();
	}
	
	@LoggedAccess(Profile.MEMBER)
	public static void create(Article article) {
		
		User user = Auth.getCurrentUser();
		EnhancedValidator validator = validator();
		validator.validate(article).require("content", "title");
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
