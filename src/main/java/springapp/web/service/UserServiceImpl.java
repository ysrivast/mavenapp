package springapp.web.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springapp.web.dao.UserDao;
import springapp.web.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	@Autowired
	HttpSession session;

	public boolean isUserValid(User user) {
		System.out.println("In user service booolean : "+userDao.validateUser(user));
		return userDao.validateUser(user);
	}
	public User getUser(User user) {
		System.out.println("In user service");
		return userDao.getUserDetail(user);
	}

	public void storeObjectInSession(User user) {
		session.setAttribute(user.getUserName(), user);
		System.out.println("storeObjectInSession method called :" + session.getAttribute(user.getUserName()));
	}

	public User getObjectFromSession(String userName) {
		User user = (User) session.getAttribute(userName);
		return user;
	}

	public void deleteObjectInSession(String userName) {
		System.out.println(session.getAttribute(userName));
		session.removeAttribute(userName);
		System.out.println("user deleted from Session");
	}

}
