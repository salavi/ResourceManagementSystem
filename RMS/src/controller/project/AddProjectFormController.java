package controller.project;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import logic.organization.project.Project;
import model.organization.project.ProjectModel;


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
	
//	private List<Technology> listOfTechnologies;
	
	@FXML
	public void handleAddTechnologyButton() {

		System.out.println("handleAddTechnologyButton");
//		Project project = new Project(projectNameId.getText(), Integer.parseInt(numOfHumansId.getText()),
//				Integer.parseInt(numOfModulesId.getText()));
//		

	}

	@FXML
	public void handleAddProjectButton() {

		System.out.println("handleAddProjectButton");
	}

}
