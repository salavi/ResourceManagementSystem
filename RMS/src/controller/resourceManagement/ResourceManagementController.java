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
		System.out.println("hello");
		showAddResourceForm();
	}
	
	private void showAddResourceForm() {
		try {
			Stage stage;
			Parent root;
			stage = (Stage) addResourceButton.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/resourceManagement/AddResourceForm.fxml"));
			root = (Parent) loader.load();
			AddResourceController addResourceController = loader.<AddResourceController> getController();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			addResourceController.initial();
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void showAllResources() {
		System.out.println("show all resources");
		
	}
}
