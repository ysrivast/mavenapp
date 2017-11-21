package springapp.web.controller;

import org.apache.log4j.Logger;
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

import springapp.web.model.User;
import springapp.web.service.UserService;

@Controller
@Scope("session")
public class HelloController {

	private static final Logger logger = Logger
			.getLogger(HelloController.class);

	@Autowired
	UserService userService;
	private String userNameInSession;
	private String view;
	private String message;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String helloUser(Model model) {
		logger.info("index page called");
		return "index";

	}

	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public String helloPage(Model model) {
		logger.info("home page called");
		return "index";

	}

	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String goToLogin(ModelMap model) {
		logger.info("In login get method" + userNameInSession);
		if (userService.getObjectFromSession(userNameInSession) != null) {
			logger.info(userService.getObjectFromSession(userNameInSession));
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
		boolean hasError;
		view=null;
		User retrivedUser;
		if (userName != null && password != null) {
			hasError = userService.isUserValid(user);
			if (hasError) {
				retrivedUser = userService.getUser(user);
				userService.storeObjectInSession(retrivedUser);
				userNameInSession = userService.getObjectFromSession(userName)
						.getUserName();
				model.addAttribute("user", retrivedUser);
				logger.info("User value from db" + retrivedUser.getUserName()
						+ retrivedUser.getGender());
				view = "userInfo";
			} else {
				message = "Incorrect username or Password";
				view = "login";
			}
		} else {
			message = "Incorrect username or Password";
			view = "login";
		}
		model.addAttribute("error", message);
		logger.error(message);
		logger.info(view
				+ " : is View name in login post method and username is session is : "
				+ userNameInSession);
		return view;

	}

	@RequestMapping(path = "/logout", method = RequestMethod.POST)
	public String goToLogout() {
		userService.deleteObjectInSession(userNameInSession);
		userNameInSession=null;
		logger.info("In logout method, deleteObjectInSession is called");
		return "login";

	}

	@RequestMapping(path = "/register", method = RequestMethod.GET)
	public String goToRegister(ModelMap model) {
		logger.info("In register get method" + userNameInSession);
		return "register";

	}

	@RequestMapping(path = "/register", method = RequestMethod.POST)
	public String createLogin(User user, ModelMap model) {
		view=null;
		System.out.println(user.getUserName() + " | " + user.getPassword()
				+ " | " + user.getGender());
		String userName = user.getUserName();
		String password = user.getPassword();
		String gender = user.getGender();
		boolean hasError;
		if (userName != null && password != null & gender != null) {
			if (userName.length() < 6) {
				view = "register";
				message = "UserName must be greater than 6 character";
				logger.error(message);
			} else if (password.length() < 6) {
				view = "register";
				message = "Password must be greater than 6 character";
				logger.error(message);
			} else if (userService.isUserValid(user)) {
				view = "register";
				message = "UserName already exists in database";
				logger.error(message);
			} else {
				boolean flag = userService.addUser(user);
				if (flag) {
					message = "You are successfully registered";
					view = "index";
					logger.error(message);
				}
			}
		} else {
			view = "register";
			message = "All fields are mandatory";
		}
		model.addAttribute("message", message);
		return view;

	}

}
