package controller.project;


import java.time.LocalDate;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import logic.organization.module.Module;
import logic.organization.project.Project;


public class AddMaintananceProcessController extends AddProcessController{
	@FXML private ListView<String> moduleList;

	private Map<String, Long> modules;
	

	public void initial() {
		super.initial();
		showModuleList();
	}

	@Override
	void handleAddButton() {
		String activity = activityNameInput.getText();
	
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
		
		String module = this.moduleList.getSelectionModel().getSelectedItem();
		Long moduleId = -1l;
		if (module != null) {
			moduleId = modules.get(module);
		}
		
		new Project().addMaintananceProcess(projectId, moduleId, unitId, activity, startDate, endDate, selectedResourcesIds);
	}
	
	private void showModuleList() {
		Module module = new Module();
		modules = module.getAllModules();
		ObservableList<String> items = FXCollections.observableArrayList(modules.keySet());
		moduleList.setItems(items);
	}
}
