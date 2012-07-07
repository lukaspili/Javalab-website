package controllers;

import controllers.abstracts.AppController;
import controllers.security.LoggedAccess;
import controllers.security.PublicAccess;
import models.events.Article;
import models.users.Profile;
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
    public static void index() {

    }
	
	@PublicAccess
	public static void allArticles() {
		
		List<Article> listArticle = new ArrayList<Article>();
		
		listArticle = Article.findAll();

		render(listArticle);
	}
	
	@LoggedAccess
	public static void newArticle() {
		render();
	}
	
	@LoggedAccess(Profile.MEMBER)
	public static void create(Article article) {
		
		EnhancedValidator validator = validator();
		validator.validate(article).require("authors", "content", "creationDate", "title");
		
		if(validator.hasErrors()) {
			render("Articles/newArtilce.html", article);
		}
		
		Article createdArticle = articleService.create(article);
		
		flash.success("Merci, l'article" + createdArticle.title + " à bien était enregistré");
		Dashboard.index();
	}
	
}
