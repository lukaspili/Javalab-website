package controllers;

import controllers.abstracts.AppController;
import controllers.security.Auth;
import controllers.security.PublicAccess;
import models.events.Article;
import models.events.Comment;
import models.users.User;
import play.cache.*;
import play.libs.*;
import play.mvc.Controller;
import validation.EnhancedValidator;
import javax.persistence.Lob;

/**
 * Created with IntelliJ IDEA.
 * User: AkeT
 * Date: 11/4/12
 * Time: 7:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class Comments extends AppController {

    @PublicAccess
    public static void create(Comment comment, String code, String randomID) {
        Long articleId = Long.valueOf(params.get("articleId"));
        Article article = Article.findById(articleId);
        EnhancedValidator validator = validator();
        validator.validate(comment).require("username", "content", "date");
        if(validator.hasErrors()) {
            flash.error("Une erreur est survenue lors de la tentative d'ajout de votre commentaire.");
            render("Articles/details.html", comment, article);
        }
        if(!code.equals(Cache.get(randomID))) {
            flash.error("Le captcha saisi ne correspond pas à celui attendu.");
            render("Articles/details.html", comment, randomID, article);
        }
        comment.save();
        article.comments.add(comment);
        article.save();
        flash.success("Votre commentaire a bien été ajouté.");
        Articles.details(articleId);
    }

    @PublicAccess
    public static void captcha(String id) {
        Images.Captcha captcha = Images.captcha();
        String code = captcha.getText("#4f4f4f");
        Cache.set(id, code, "10mn");
        renderBinary(captcha);
    }
}
