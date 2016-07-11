package controller.resourceAllocation;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import logic.organization.project.Project;

public class ResourceAllocationController {

	@FXML
	private Button addAllocatedResourceButtonId;
	@FXML
	private Button addProjectButtonId;
	@FXML
	private Button addRequiredResourceButtonId;
	@FXML
	private TableView<String> tableViewId;
	@FXML
	private TableColumn<String,String> projectsColumn;
	

	Map<String, Long> projectsMap;
	
	public ResourceAllocationController(){
		
	}
	
	@FXML
	public void handleAddProjectButton() {

		System.out.println("handleAddProjectButton");
		Stage stage;
		Parent root;
		stage = (Stage) addProjectButtonId.getScene().getWindow();
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
		Stage stage;
		Parent root;
		stage = (Stage) addAllocatedResourceButtonId.getScene().getWindow();
		try {
			root = FXMLLoader.load(getClass().getResource("/view/resourceAllocation/AddAllocatedResourceForm.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	public void handleRequiredResourceButton() {
		Stage stage;
		Parent root;
		stage = (Stage) addRequiredResourceButtonId.getScene().getWindow();
		try {
			root = FXMLLoader.load(getClass().getResource("/view/resourceAllocation/AddRequiredResourceForm.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showAllProjects() {
		System.out.println("fetchProjects");
		Project project = new Project();
		this.projectsMap = project.getAllProjects();
		ObservableList<String> data = FXCollections.observableArrayList();
		data.addAll(projectsMap.keySet());
		System.out.println(data);
		tableViewId.getItems().setAll(data);

	}
}
