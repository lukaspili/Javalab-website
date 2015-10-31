package controllers;

import models.Gift;
import models.Member;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class Gifts extends Controller {
	
	static Form<Gift> giftForm = form(Gift.class);
	
	public static Result all() {
		return TODO;
	}
	
	public static Result newGift() {
		Form<Gift> filledForm = giftForm.bindFromRequest();
		/*if (filledForm.hasErrors()) {
			return badRequest(
				views.html.index.render(Gift.all(), filledForm)
			);
		} else {*/
			Gift.create(filledForm.get());
			return redirect(routes.Members.all());
		//}
	}
	
	public static Result deleteMember(Long id) {
		return TODO;
	}

}
