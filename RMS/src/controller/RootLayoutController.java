package controller;

import java.io.IOException;

import controller.resourceAllocation.ResourceAllocationController;
import controller.userAccount.UserAccountController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.stage.Stage;
import logic.authentication.Authentication;

public class RootLayoutController {

	@FXML
	private UserAccountController userAccountController;
	@FXML
	private ResourceAllocationController resourceAllocationController;
	@FXML
	private Button logoutButton;
	@FXML
	private Tab resourceAllocationTabId;

	private Authentication auth;

	public RootLayoutController() {
		auth = Authentication.getInstance();
	}

	public Authentication getAuth() {
		return auth;
	}

	public void setAuth(Authentication auth) {
		this.auth = auth;
	}

	public UserAccountController getUserAccountController() {
		return userAccountController;
	}

	public void setUserAccountController(UserAccountController userAccountController) {
		this.userAccountController = userAccountController;
	}

	@FXML
	private void handleLogoutButton() {
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

	@FXML
	private void onResourceAllocationSelection() {
		if (resourceAllocationTabId.isSelected()) {
			System.out.println("ResourceAllocation is selected");
			System.out.println(resourceAllocationController == null);
			resourceAllocationController.showAllProjects();
		}
	}

}
