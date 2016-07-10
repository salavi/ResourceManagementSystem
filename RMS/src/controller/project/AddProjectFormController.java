package controller.project;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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

		technologyNameId.setText("");
		goalOfTechId.setText("");
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

}
