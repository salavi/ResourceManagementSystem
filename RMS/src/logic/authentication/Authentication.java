package logic.authentication;

import model.UserAccountAdapter;

public class Authentication {
	UserAccount loggedInUser;

	public UserAccount login(String username, String password){
		System.out.println("in Authentication");
		return null;
	}

	public int signUp(String firstName, String lastName, String username, String password){		
		UserAccountAdapter userAccountAdapter = new UserAccountAdapter();
		userAccountAdapter.addUserAccount(firstName, lastName, username, password);		
		return 1;
	}
}
