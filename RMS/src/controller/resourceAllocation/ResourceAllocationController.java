package controller.resourceAllocation;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import controller.resourceManagement.AddResourceController;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import logic.organization.project.Project;
import model.organization.project.ProjectModel;

public class ResourceAllocationController {

	@FXML
	private Button addAllocatedResourceButtonId;
	@FXML
	private Button addProjectButtonId;
	@FXML
	private Button addRequiredResourceButtonId;
	@FXML
	private TableView<ProjectModel> tableViewId;
	@FXML
	private TableColumn<ProjectModel, String> projectsColumn;
	
	private ObservableList<ProjectModel> retrievedProjectData = FXCollections.observableArrayList();

	private List<ProjectModel> projectsList;

	public ResourceAllocationController() {

	}

	public void initialize() {
		
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
		FXMLLoader loader = new FXMLLoader();
		try {
			loader.setLocation(getClass().getResource("/view/resourceAllocation/AddRequiredResourceForm.fxml"));
			root = (Parent) loader.load();
			AddRequiredResourceController addRequiredResourceController = loader.<AddRequiredResourceController> getController();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			addRequiredResourceController.initial();
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showAllProjects() {
		System.out.println("fetchProjects");
		Project project = new Project();
		this.projectsList = project.getAllProjects();
		changeToRetrievedProjects(this.projectsList);
		
//		ObservableList<String> data = FXCollections.observableArrayList();
//		data.addAll(projectsMap.keySet());
//		System.out.println(data);
//		tableViewId.getItems().setAll(data);
	}

	private void changeToRetrievedProjects(List<ProjectModel> projectsList) {
		// TODO Auto-generated method stub
		for(ProjectModel projectModel: projectsList){
			retrievedProjectData.add(projectModel);
		}
		
		projectsColumn.setCellValueFactory(new PropertyValueFactory<ProjectModel, String>("پروژه‌های سازمان"));
//		projectsColumn.setCellValueFactory(new PropertyValueFactory<ProjectModel, String>("firstName"));
//		projectsColumn.setCellValueFactory(
//			    new PropertyValueFactory<ProjectModel,String>("پروژه‌های سازمان")
//			);
		
		tableViewId.setItems(retrievedProjectData);
//		tableViewId.getColumns().add(projectsColumn);
	}

}

