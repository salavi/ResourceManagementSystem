package controller.project;

import java.io.IOException;

import controller.resourceManagement.AddResourceController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DevelopementProcessController {
	@FXML Button addDevProcessButton;
	@FXML Button addMainProcessButton;
	
	@FXML
	private void handleAddDevProcessButton() {
		showAddDevelopementProcessForm();
	}
	
	@FXML
	private void handleAddMainProcessButton() {
		showAddMaintananceProcessForm();
	}
	
	private void showAddDevelopementProcessForm() {
		try {
			Stage stage;
			Parent root;
			stage = (Stage) addDevProcessButton.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/project/AddDevelopementProcessForm.fxml"));
			root = (Parent) loader.load();
			AddDevelopementProcessController addDevelopementProcessController = loader.<AddDevelopementProcessController> getController();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			addDevelopementProcessController.initial();
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void showAddMaintananceProcessForm() {
		try {
			Stage stage;
			Parent root;
			stage = (Stage) addDevProcessButton.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/project/AddMaintananceProcessForm.fxml"));
			root = (Parent) loader.load();
			AddMaintananceProcessController addMaintananceProcessController = loader.<AddMaintananceProcessController> getController();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			addMaintananceProcessController.initial();
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
