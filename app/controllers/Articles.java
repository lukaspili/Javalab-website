package controllers;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import service.ArticleService;
import service.UserService;
import validation.EnhancedValidator;

import controllers.filters.security.LoggedAccess;
import controllers.filters.security.PublicAccess;
import controllers.logicals.LogicController;
import models.events.*;

/**
 * @author Kevin Valfin
 */
public class Articles extends LogicController {
	
	@Inject
	private static ArticleService articleService;
	
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
	
	@LoggedAccess
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
