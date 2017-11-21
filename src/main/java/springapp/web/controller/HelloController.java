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
	@Autowired
	User user;
	@InitBinder
	public void initBinder(WebDataBinder binder){
		
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
	public String GoToLogin(HttpSession session) {
		System.out.println("In login get method");
		String message = "You are not sopposed to be welcome here";
		return "login";

	}

	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String checkLogin(User user, ModelMap model,HttpSession session) {
		System.out.println("In login post method");
		System.out.println(user.getUserName() + " | " + user.getPassword());
		String userName = user.getUserName();
		String password = user.getPassword();
		String message=null;
		String view;
		System.out.println("User value from request"+user.getUserName()
				+ user.getGender());
		User retrivedUser;
		if (userName != null && password != null) {
			retrivedUser = userService.isUserValid(user);
			model.addAttribute("user",retrivedUser);
			System.out.println("User value from db"+retrivedUser.getUserName()
					+ retrivedUser.getGender());
			if(retrivedUser.getUserName()!=null){
			view="userInfo";
			}
			else{
				message="Incorrect username or paswword";
				view="login";
			}
		} else if (userName == null) {
			message = "Enter username";
			//model.addAttribute("error", message);
			System.out.println(message);
			view="login";
		} else {
			message = "Enter password";
			//model.addAttribute("error", message);
			System.out.println(message);
			view="login";
		}
		model.addAttribute("error", message);
		System.out.println(view);
		return view;

	}

}
