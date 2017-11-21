package springapp.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springapp.web.dao.UserDao;
import springapp.web.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	public User isUserValid(User user) {
		System.out.println("In user service");

		return userDao.validateUser(user);
	}

	public void storeUserInSession() {
	}

	public User getStoredUserInSession() {
		return null;
	}

}
