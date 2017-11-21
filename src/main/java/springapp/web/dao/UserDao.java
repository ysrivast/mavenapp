package springapp.web.dao;

import springapp.web.model.User;

public interface UserDao {
	
	boolean registerUser(User user);
	boolean validateUser(User user);
	User getUserDetail(User user);
	
}
