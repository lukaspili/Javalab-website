package service;

import models.events.Article;
import models.users.User;

import java.util.List;

/**
 * @author Kevin Valfin
 */
public class ArticleService {

	public Article create(Article article){
		
		Article articleToCreate = new Article();
		articleToCreate.author = article.author;
		articleToCreate.content = article.content;
		articleToCreate.creationDate = article.creationDate;
		articleToCreate.title = article.title;
		
		articleToCreate.save();
		
		return articleToCreate; 
	}
    
    public List<Article> getArticlesByUser(User user) {
        return Article.find("author = ? order by creationDate desc", user).fetch();
    }
}
