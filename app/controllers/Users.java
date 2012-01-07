package controllers;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import models.users.Campus;
import models.users.Promotion;
import models.users.User;
import play.Logger;
import play.mvc.Before;
import service.UserService;
import validation.EnhancedValidator;
import controllers.filters.AuthFilter;
import controllers.filters.security.LoggedAccess;
import controllers.filters.security.PublicAccess;
import controllers.helpers.controller.PageHelper;
import controllers.logicals.LogicController;

/**
 * @author Lukasz Piliszczuk <lukasz.piliszczuk AT zenika.com>
 */

public class Users extends LogicController {
	
	@Inject
	private static UserService userService;

	private static PageHelper pageHelper;

	@Before
	public static void before() {
		pageHelper = new PageHelper("users", renderArgs);
	}

	@PublicAccess(only = true)
	public static void login() {
		render();
	}

	@PublicAccess(only = true)
	public static void authenticate(User user) {

		EnhancedValidator validator = validator();

		if (validator.validate(user).require("idBooster").hasErrors()) {
			render("Users/login.html", user);
		}

		User userFromDb = User.find("byIdBooster", user.idBooster).first();

		if (null == userFromDb) {
			flashErrorSamePage("users.login.authentication.failure");
			render("Users/login.html", user);
		}

		AuthFilter.loginUser(userFromDb);

		Dashboard.index();
	}

	@LoggedAccess
	public static void logout() {
		Logger.debug("foobar");
		AuthFilter.logoutUser();
		Dashboard.index();
	}

	@LoggedAccess
	public static void profile() {
		User user = AuthFilter.getCurrentUser();
		List<Promotion> promotionList = new ArrayList<Promotion>();
		for(Promotion promotion : Promotion.values()) {
			promotionList.add(promotion);
		}
		List<Campus> campusList = new ArrayList<Campus>();
		for(Campus campus : Campus.values()) {
			campusList.add(campus);
		}
		render(user, promotionList, campusList);
	}

	@PublicAccess(only = true)
	public static void newUser() {
		render();
	}
	
	@PublicAccess(only = true)
	public static void create(User user) {
		
		EnhancedValidator validator = validator();
		validator.validate(user).require("idBooster", "firstName", "lastName", "promotion", "campus");
		
		if(validator.hasErrors()) {
			render("Users/newUser.html", user);
		}
		
		User createdUser = userService.create(user);
		
		flash.success("Merci " + createdUser.firstName + createdUser.lastName + " vous êtes bien enregistré");
		Dashboard.index();
	}
	

	@LoggedAccess
	public static void modify(String firstName, String lastName, String campus) {
		System.out.println(firstName);
		System.out.println(lastName);
		User user = AuthFilter.getCurrentUser();
		user.firstName = firstName;
		user.lastName = lastName;
		user.campus = Campus.valueOf(campus);
		user.save();
		flash.success("Votre profile a été modifié !");
		profile();
	}
	
	@LoggedAccess
	public static void details(String idBooster) {
		User user = User.find("byIdBooster", idBooster).first();
		render(user);
	}
	
	/**
	 * Members list
	 */
	public static void list() {

	}

	public static void list(Campus campus) {

	}
}
