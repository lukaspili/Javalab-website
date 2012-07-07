package service;

import models.events.Article;

/**
 * @author Kevin Valfin
 */
public class ArticleService {

	public Article create(Article article){
		
		Article articleToCreate = new Article();
		articleToCreate.authors = article.authors;
		articleToCreate.content = article.content;
		articleToCreate.creationDate = article.creationDate;
		articleToCreate.title = article.title;
		
		articleToCreate.save();
		
		return articleToCreate; 
	}
}
