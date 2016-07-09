package controller.resourceAllocation;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ResourceAllocation {

	@FXML
	private Button AddResourceToProjectButtonId;
	@FXML
	private Button AddProjectButtonId;

	@FXML
	public void handleAddProjectButton() {

		System.out.println("handleAddProjectButton");
		Stage stage;
		Parent root;
		stage = (Stage) AddProjectButtonId.getScene().getWindow();
		try {
			root = FXMLLoader.load(getClass().getResource("/view/project/AddProjectForm.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void handleAddResourceToProjectButton() {

		System.out.println("handleAddResourceToProjectButton");
	}
}
