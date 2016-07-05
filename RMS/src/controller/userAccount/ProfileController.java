package controller.userAccount;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import logic.authentication.UserAccount;

public class ProfileController {
	@FXML Label firstName;
	@FXML Label lastName;
	@FXML Label username;
	@FXML Label accessLevel;
	
	private UserAccount loggedInUser;
	
	public void initialize(){
		System.out.println("initializeeeeeeeeeeee");
//		firstName.setText(loggedInUser.getFirstName());
//		lastName.setText(loggedInUser.getLastName());
//		username.setText(loggedInUser.getUsername());
//		accessLevel.setText(loggedInUser.getAccessLevel().toString());
	}

	public UserAccount getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(UserAccount loggedInUser) {
		this.loggedInUser = loggedInUser;
	}
	
	
}
