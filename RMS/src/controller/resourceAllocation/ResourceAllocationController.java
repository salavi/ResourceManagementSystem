package controller.resourceAllocation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import controller.project.AddProjectFormController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import logic.organization.project.Project;
import logic.organization.project.Technology;
import model.organization.project.ProjectModel;

public class ResourceAllocationController {

	@FXML
	private Button addAllocatedResourceButtonId;
	@FXML
	private Button addProjectButtonId;
	@FXML
	private Button addRequiredResourceButtonId;
	@FXML
	private TreeView<String> projectListTreeId;
	@FXML
	private Label projectNameLabelId;
	@FXML
	private Label numOfHumanLabelId;
	@FXML
	private Label numOfModuleLabelId;
	@FXML
	private ListView<String> listOfTechnologiesListId;
	@FXML
	private GridPane gridPaneId;
	
	
	private ObservableList<ProjectModel> retrievedProjectData = FXCollections.observableArrayList();

	private Map<String, Long> projectsList;

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
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/project/AddProjectForm.fxml"));
			root = (Parent) loader.load();
			AddProjectFormController addProjectFormController = loader.<AddProjectFormController> getController();
			addProjectFormController.setReturnState("/view/RootLayout.fxml");
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void handleAllocatedResourceButton() {
		Stage stage;
		Parent root;
		stage = (Stage) addAllocatedResourceButtonId.getScene().getWindow();
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/resourceAllocation/AddAllocatedResourceForm.fxml"));
			root = (Parent) loader.load();
			AddAllocatedResourceController addAllocatedResourceController = loader.<AddAllocatedResourceController> getController();
			Scene scene = new Scene(root);
			addAllocatedResourceController.initial();
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
	
	private List<TreeItem<String>> createTreeItems(Set<String> keys) {
		List<TreeItem<String>> children = new ArrayList<TreeItem<String>>();
		for (String key : keys) {
			TreeItem<String> item = new TreeItem<String> (key);
			children.add(item);
		}
		
		return children;
	}

	private void showProjectInfo(String key) {
		this.gridPaneId.setVisible(true);

		Long projectId = projectsList.get(key);
		Project project = new Project().getProject(projectId);
		
		this.projectNameLabelId.setText(project.getName());
		this.numOfHumanLabelId.setText(project.getNumOfInvolvedHumans() + "");
		this.numOfModuleLabelId.setText(project.getNumOfModules() + "");
		
		this.listOfTechnologiesListId.setVisible(true);
		ObservableList<String> items = FXCollections.observableArrayList();
		for (Technology tech : project.getListOfTechnologies()) {
			items.add(tech.getName());
		}
		this.listOfTechnologiesListId.setItems(items);
	}

	public void showAllProjects() {
		this.gridPaneId.setVisible(false);
		this.listOfTechnologiesListId.setVisible(false);
		TreeItem<String> projectRoot = new TreeItem<String> ("پروژه های سازمان");
		projectRoot.setExpanded(true);
		projectsList = new Project().getAllProjects();
		projectRoot.getChildren().addAll(createTreeItems(projectsList.keySet()));
		this.projectListTreeId.setRoot(projectRoot);
		this.projectListTreeId.getSelectionModel().selectedItemProperty().addListener( new ChangeListener() {

	        @Override
	        public void changed(ObservableValue observable, Object oldValue,
	                Object newValue) {

	            TreeItem<String> selectedItem = (TreeItem<String>) newValue;
	            String key = selectedItem.getValue();
	            showProjectInfo(key);
	        }
	      });
	}

}

