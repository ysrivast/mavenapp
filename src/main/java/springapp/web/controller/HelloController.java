package springapp.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.WebBindingInitializer;

import springapp.web.model.User;
import springapp.web.service.UserService;

@Controller
@Scope("session")
public class HelloController {

	@Autowired
	UserService userService;
	String userNameInSession;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String helloUser(Model model) {
		System.out.println("index page called");
		String message = "You are not sopposed to be welcome here";
		model.addAttribute("message", message);
		return "index";

	}

	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public String helloPage(Model model) {
		System.out.println("home page called");
		String message = "You are not sopposed to be welcome here";
		model.addAttribute("message", message);
		return "index";

	}

	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String goToLogin(ModelMap model) {
		String view;
		System.out.println("In login get method" + userNameInSession);
		if (userService.getObjectFromSession(userNameInSession) != null) {
			System.out.println(userService
					.getObjectFromSession(userNameInSession));
			return checkLogin(
					userService.getObjectFromSession(userNameInSession), model);
		}
		return "login";

	}

	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String checkLogin(User user, ModelMap model) {
		System.out.println(user.getUserName() + " | " + user.getPassword());
		String userName = user.getUserName();
		String password = user.getPassword();
		String message = null;
		String view = null;
		boolean hasError;
		User retrivedUser;
		if (userName != null && password != null) {
			hasError = userService.isUserValid(user);
			if (hasError) {
				retrivedUser = userService.getUser(user);
				userService.storeObjectInSession(retrivedUser);
				userNameInSession = userService.getObjectFromSession(userName)
						.getUserName();
				model.addAttribute("user", retrivedUser);
				System.out
						.println("User value from db"
								+ retrivedUser.getUserName()
								+ retrivedUser.getGender());
				view = "userInfo";
			}
			else {
				message = "Incorrect username or Password";
				view = "login";
			}
		} else {
			message = "Incorrect username or Password";
			view = "login";
		}
		model.addAttribute("error", message);
		System.out
				.println(view
						+ " : is View name in login post method and username is session is : "
						+ userNameInSession);
		return view;

	}

	@RequestMapping(path = "/logout", method = RequestMethod.POST)
	public String goToLogout() {
		userService.deleteObjectInSession(userNameInSession);
		System.out.println("In logout method, deleteObjectInSession is called");
		return "login";

	}

}
