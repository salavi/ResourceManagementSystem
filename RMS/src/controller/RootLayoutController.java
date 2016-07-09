package controller;

import java.io.IOException;

import controller.resourceManagement.ResourceManagementController;
import controller.userAccount.UserAccountController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import logic.authentication.UserAccount;

public class RootLayoutController {

	@FXML
	private UserAccountController userAccountController;
	@FXML
	private Button logoutButton;
	
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
	
	@FXML
	private void handleLogoutButton(){
		Stage stage; 
		Parent root;
		stage = (Stage) logoutButton.getScene().getWindow();
		try {
			root = FXMLLoader.load(getClass().getResource("/view/userAccount/LoginUI.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
