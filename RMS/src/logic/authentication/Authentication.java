package logic.authentication;

import java.util.Iterator;
import java.util.List;

import model.authentication.UserAccountAdapter;
import model.authentication.UserAccountModel;

public class Authentication {
	UserAccount loggedInUser;

	public UserAccount login(String username, String password){
		boolean userFound = false;
		UserAccountAdapter userAccountAdapter = UserAccountAdapter.getInstance();
		List<UserAccountModel> users = userAccountAdapter.findAll();
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
}
