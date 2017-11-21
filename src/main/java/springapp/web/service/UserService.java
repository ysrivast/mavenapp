package springapp.web.service;

import springapp.web.model.User;

public interface UserService {

	public void storeObjectInSession(User user);

	User getObjectFromSession(String userName);
	
	void deleteObjectInSession(String userName);

	public boolean isUserValid(User user);
	
	public User getUser(User user);

	
}
