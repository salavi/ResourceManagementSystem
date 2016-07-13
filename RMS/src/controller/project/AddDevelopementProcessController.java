package controller.project;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Version;

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
		Long unitId = -1l;
		if (unit != null) {
			unitId = units.get(unit);
		}

		String project = this.projectList.getSelectionModel().getSelectedItem();
		Long projectId = -1l;
		if (project != null) {
			projectId = projects.get(project);
		}

		new Project().addDevelopementProcess(projectId, unitId, activity, moduleName, moduleId, startDate, endDate,
				selectedResourcesIds);
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

}
