package controller;

import java.io.IOException;

import controller.prediction.PredictionController;
import controller.project.DevelopementProcessController;
import controller.resourceAllocation.ResourceAllocationController;
import controller.resourceManagement.ResourceManagementController;
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
	private ResourceManagementController resourceManagementController;
	@FXML
	private DevelopementProcessController developmentProcessController;
	@FXML
	private PredictionController predictionController;
	@FXML
	private Button logoutButton;
	@FXML
	private Tab resourceAllocationTabId;
	@FXML
	private Tab resourceManagementTabId;
	@FXML
	private Tab processTabId;
	@FXML
	private Tab predictionTabId;

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
			Authentication.setInstanceToNull();
			root = FXMLLoader.load(getClass().getResource("/view/userAccount/LoginUI.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML void onProcessSelection() {
		if (processTabId.isSelected()) {
			developmentProcessController.showAllActivities();
		}
	}

	@FXML
	private void onResourceAllocationSelection() {
		if (resourceAllocationTabId.isSelected()) {
			resourceAllocationController.showAllProjects();
		}
	}

	@FXML
	private void onResourceManagementSelection() {
		if (resourceManagementTabId.isSelected()) {
			resourceManagementController.showAllResources();
		}
	}

	@FXML
	private void onPredictionSelection() {
//		if (predictionTabId.isSelected()) {
//			predictionController.getResourcePredictionController().showAllTechnologies();
//		}
	}

}

