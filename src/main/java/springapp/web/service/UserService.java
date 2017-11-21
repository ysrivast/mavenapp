package springapp.web.service;

import springapp.web.model.User;

public interface UserService {
	
	public void storeUserInSession();
	
	public User getStoredUserInSession();

	public User isUserValid(User user);
}
