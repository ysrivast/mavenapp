package springapp.web.service;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springapp.web.controller.HelloController;
import springapp.web.dao.UserDao;
import springapp.web.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	@Autowired
	HttpSession session;
	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);
	public boolean isUserValid(User user) {
		logger.info("In user service booolean : "+userDao.validateUser(user));
		return userDao.validateUser(user);
	}
	public User getUser(User user) {
		logger.info("In user service");
		return userDao.getUserDetail(user);
	}

	public void storeObjectInSession(User user) {
		session.setAttribute(user.getUserName(), user);
		logger.info("storeObjectInSession method called :" + session.getAttribute(user.getUserName()));
	}

	public User getObjectFromSession(String userName) {
		User user = (User) session.getAttribute(userName);
		return user;
	}

	public void deleteObjectInSession(String userName) {
		logger.info(session.getAttribute(userName));
		session.removeAttribute(userName);
		logger.info("user deleted from Session");
	}
	public boolean addUser(User user) {
		logger.info("In user service");
		return userDao.registerUser(user);
	}

}
