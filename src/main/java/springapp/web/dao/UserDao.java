package springapp.web.dao;

import springapp.web.model.User;

public interface UserDao {
	
	void registerUser();
	User validateUser(User user);
	
}
