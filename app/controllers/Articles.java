package controllers;

import java.util.ArrayList;
import java.util.List;

import controllers.logicals.LogicController;
import models.events.*;

public class Articles extends LogicController {
	
	public static void all() {
		
		List<Article> listArticle = new ArrayList<Article>();
		
		listArticle = Article.findAll();

		render(listArticle);
	}
}
