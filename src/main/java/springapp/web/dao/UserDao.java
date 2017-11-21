package springapp.web.dao;

import springapp.web.model.User;

public interface UserDao {
	
	void registerUser();
	boolean validateUser(User user);
	User getUserDetail(User user);
	
}
