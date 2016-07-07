package controller;

import controller.userAccount.UserAccountController;
import javafx.fxml.FXML;
import logic.authentication.UserAccount;

public class RootLayoutController {

	@FXML
	private UserAccountController userAccountController;
	private UserAccount loggedInUser;

	public RootLayoutController() {
		// TODO Auto-generated constructor stub
	}

	public UserAccount getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(UserAccount loggedInUser) {
		this.loggedInUser = loggedInUser;
	}

	public UserAccountController getUserAccountController() {
		return userAccountController;
	}

	public void setUserAccountController(UserAccountController userAccountController) {
		this.userAccountController = userAccountController;
	}
}
