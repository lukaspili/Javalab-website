package controllers;

import models.Member;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.signup;



public class SignUp extends Controller {
	
	static Form<Member> signUpForm = form(Member.class);

	
	public static Result blank() {
        return ok(signup.render(signUpForm));
    }
	
	public static Result submit(){
		
		Form<Member> filledForm = signUpForm.bindFromRequest();
		
		if(filledForm.hasErrors()){
			return badRequest(signup.render(filledForm));

		}else{
			Member created = filledForm.get();
			Member.create(created);
			return redirect(routes.Members.all());
		}	
	}
}
