package controller.resourceManagement;



import java.io.IOException;

import controller.RootLayoutController;
import controller.userAccount.UserAccountController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ResourceManagementController {
	@FXML private Button addResourceButton;
	
	
	@FXML
	private void handleAddResourceButton() {
		
	}
	
	private void showAddResourcePage() {
//		try {
//			Stage stage;
//			Parent root;
//			stage = (Stage) addResourceButton.getScene().getWindow();
//			FXMLLoader loader = new FXMLLoader();
//			loader.setLocation(getClass().getResource("/view/RootLayout.fxml"));
//			root = (Parent) loader.load();
//			RootLayoutController rootLayoutController = loader.<RootLayoutController> getController();
//			rootLayoutController.setLoggedInUser(loggedInUser);
//			UserAccountController userAccountController =rootLayoutController.getUserAccountController();
//			userAccountController.setLoggedInUser(loggedInUser);
//			userAccountController.getProfileController().setLoggedInUser(loggedInUser);
//			userAccountController.getProfileController().setAllLabels();
//			userAccountController.getEditProfileController().setLoggedInUser(loggedInUser);
//
//			Scene scene = new Scene(root);
//			stage.setScene(scene);
//			stage.show();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}
}
