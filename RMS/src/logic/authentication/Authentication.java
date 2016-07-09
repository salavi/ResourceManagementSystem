package logic.authentication;

import java.util.Iterator;
import java.util.List;

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
		boolean userFound = false;
		UserAccountAdapter userAccountAdapter = UserAccountAdapter.getInstance();
		List<UserAccountModel> users = userAccountAdapter.findAll();
//		System.out.println("users is null:" + (users == null));
		Iterator<UserAccountModel> it = users.iterator();
		while(it.hasNext()) {
			UserAccountModel temp = it.next();
			userFound = temp.authenticate(username, password);
			if(userFound){
				loggedInUser = temp.getUserAccount();
				return loggedInUser;
			}
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
