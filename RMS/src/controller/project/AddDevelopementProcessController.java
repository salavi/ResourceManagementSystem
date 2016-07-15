package controller.project;

import java.io.IOException;
import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.organization.project.Project;

public class AddDevelopementProcessController extends AddProcessController {
	@FXML
	private TextField moduleNameInput;
	@FXML
	private TextField moduleIdInput;

	@Override
	void handleAddButton() {
		String activity = activityNameInput.getText();
		String moduleName = moduleNameInput.getText();
		String moduleId = moduleIdInput.getText();

		LocalDate startDate = startDateInput.getValue();
		LocalDate endDate = endDateInput.getValue();

		String unit = this.unitList.getSelectionModel().getSelectedItem();
		String project = this.projectList.getSelectionModel().getSelectedItem();

		if (!isInputValid(activity, moduleName, moduleId, unit, project, startDate, endDate))
			System.out.println("error");

		else {
			Long unitId = units.get(unit);
			Long projectId = projects.get(project);
			new Project().addDevelopementProcess(projectId, unitId, activity, moduleName, moduleId, startDate, endDate,
					selectedResourcesIds);
		}

		this.clear();
	}

	private boolean isInputValid(String activity, String moduleName, String moduleId, String unit, String project,
			LocalDate startDate, LocalDate endDate) {
		return activity != null && moduleName != null && moduleId != null && unit != null && project != null
				&& startDate != null && endDate != null;
	}

	@Override
	protected void handleAddProjectButton() {

		System.out.println("handleAddProjectButton");
		Stage stage;
		Parent root;
		stage = (Stage) addProjectButton.getScene().getWindow();
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/project/AddProjectForm.fxml"));
			root = (Parent) loader.load();
			AddProjectFormController addProjectFormController = loader.<AddProjectFormController> getController();
			addProjectFormController.setReturnState("/view/project/AddDevelopementProcessForm.fxml");
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void clear() {
		super.clear();
		this.moduleIdInput.clear();
		this.moduleNameInput.clear();
	}

}
