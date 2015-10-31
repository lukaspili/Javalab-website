package controllers;

import models.Member;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

@Security.Authenticated(Secured.class)
public class Members extends Controller{

	static Form<Member> memberForm = form(Member.class);
	
	public static Result all() {
		return ok(
				views.html.members.all.render(Member.findAll())
		);
	}
	
	public static Result newMember() {
		Form<Member> filledForm = memberForm.bindFromRequest();
		/*if (filledForm.hasErrors()) {
			return badRequest(
				views.html.index.render(Member.all(), filledForm)
			);
		} else {*/
			Member.create(filledForm.get());
			return redirect(routes.Members.all());
		//}
	}
	
	public static Result deleteMember(Long id) {
		return TODO;
	}
	
}
