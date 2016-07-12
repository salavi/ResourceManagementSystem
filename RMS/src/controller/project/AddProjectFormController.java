package controller.project;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.organization.project.Process;
import logic.organization.project.Project;
import logic.organization.project.Technology;

public class AddProjectFormController {
	@FXML
	private TextField projectNameId;
	@FXML
	private TextField numOfHumansId;
	@FXML
	private TextField numOfModulesId;
	@FXML
	private TextField technologyNameId;
	@FXML
	private TextField goalOfTechId;
	@FXML
	private Button addTechnologyButtonId;
	@FXML
	private Button addProjectButtonId;
	@FXML
	private Button returnButtonId;
	@FXML
	private ListView<String> technologyListViewId;
	

	private ArrayList<Technology> listOfTechnologies;
	private int numberOfTechnologies;
	ObservableList<String> data;

	public AddProjectFormController() {
		listOfTechnologies = new ArrayList<>();
		data = FXCollections.observableArrayList();
	}

	@FXML
	public void handleAddTechnologyButton() {

		System.out.println("handleAddTechnologyButton");
		String technologyName = technologyNameId.getText();
		String technologyGoal = goalOfTechId.getText();
		Technology technology = new Technology(technologyName, technologyGoal);
		listOfTechnologies.add(technology);
		data.add(Integer.toString(++numberOfTechnologies) + "." + "نام تکنولوژی:" + technologyName + "\n   "
				+ "هدف استفاده:" + technologyGoal);
		technologyListViewId.setItems(data);

		technologyNameId.clear();;
		goalOfTechId.clear();;
	}

	@FXML
	public void handleAddProjectButton() {

		System.out.println("handleAddProjectButton");
		Process developmentProcess = new Process("DevelopmentProcess");
		Process maintananceProcess = new Process("MaintananceProcess");
		Project project = new Project(projectNameId.getText(), Integer.parseInt(numOfHumansId.getText()),
				Integer.parseInt(numOfModulesId.getText()), developmentProcess, maintananceProcess, listOfTechnologies);
		project.addProjectToDB();
	}
	
	@FXML
	public void handleReturnButton(){
		Stage stage;
		Parent root;
		stage = (Stage) returnButtonId.getScene().getWindow();
		try {
			root = FXMLLoader.load(getClass().getResource("/view/RootLayout.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
