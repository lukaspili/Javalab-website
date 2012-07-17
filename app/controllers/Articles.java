package controllers;

import controllers.abstracts.AppController;
import controllers.security.Auth;
import controllers.security.LoggedAccess;
import controllers.security.PublicAccess;
import models.events.Article;
import models.events.Project;
import models.users.Profile;
import models.users.User;
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
		
		List<Article> listArticle = new ArrayList<Article>();
		
		listArticle = Article.findAll();

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
			render("Articles/newArticle.html", article);
		}
		
		Article createdArticle = articleService.create(article);
		
		flash.success("Merci, l'article " + createdArticle.title + " à bien était enregistré");
		Articles.index();
	}
	
	@LoggedAccess
    public static void details(long articleId) {

        Article article = Article.findById(articleId);
        notFoundIfNull(article);

        
        render(article);
    }

	
}
