package controller.project;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Version;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import logic.organization.project.Project;

public class AddDevelopementProcessController extends AddProcessController {
	@FXML private TextField moduleNameInput;
	@FXML private TextField moduleIdInput;
	
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
		
		new Project().addDevelopementProcess(projectId, unitId, activity, moduleName, moduleId, startDate, endDate, selectedResourcesIds);
	}
	
	
	
}
