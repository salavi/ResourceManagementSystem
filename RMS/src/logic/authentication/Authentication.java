package logic.authentication;

import model.authentication.UserAccountAdapter;
import model.authentication.UserAccountModel;

public class Authentication {
	private static Authentication instance = null;

	private UserAccount loggedInUser;

	private Authentication(){

	}

	public static Authentication getInstance(){
		if(instance == null) {
			instance = new Authentication();
		}
		return instance;
	}

	public static void setInstanceToNull(){
		instance = null;
	}

	public UserAccount login(String username, String password){
		UserAccountAdapter userAccountAdapter = UserAccountAdapter.getInstance();
		UserAccountModel user = userAccountAdapter.find(username);
		boolean isAuthenticated = user.authenticate(username, password);
		if(isAuthenticated){
			loggedInUser = user.getUserAccount();
			return loggedInUser;
		}
		return null;
	}

	public int signUp(String firstName, String lastName, String username, String password){	
		UserAccountModel userAcModel = new UserAccountModel(firstName, lastName, username, password);
		UserAccountAdapter userAccountAdapter = UserAccountAdapter.getInstance();
		return userAccountAdapter.addUserAccount(userAcModel);		
	}

	public UserAccount getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(UserAccount loggedInUser) {
		this.loggedInUser = loggedInUser;
	}
}
