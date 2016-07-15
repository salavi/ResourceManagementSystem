package controller.project;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import logic.organization.module.Module;
import logic.organization.project.Project;

public class AddMaintananceProcessController extends AddProcessController {
	@FXML
	private ListView<String> moduleList;

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
		String project = this.projectList.getSelectionModel().getSelectedItem();
		String module = this.moduleList.getSelectionModel().getSelectedItem();
		if (!isInputValid(activity, startDate, endDate, unit, project, module))
			System.out.println("error");
		else {
			Long unitId = units.get(unit);
			Long projectId = projects.get(project);
			Long moduleId = modules.get(module);
			new Project().addMaintananceProcess(projectId, moduleId, unitId, activity, startDate, endDate,
					selectedResourcesIds);
		}
		this.clear();
	}

	private boolean isInputValid(String activity, LocalDate startDate, LocalDate endDate, String unit, String project,
			String module) {
		return activity != null && startDate != null && endDate != null && unit != null && project != null
				&& module != null;
	}

	private void showModuleList() {
		Module module = new Module();
		modules = module.getAllModules();
		ObservableList<String> items = FXCollections.observableArrayList(modules.keySet());
		moduleList.setItems(items);
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
			addProjectFormController.setReturnState("/view/project/AddMaintananceProcessForm.fxml");
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
		this.moduleList.getSelectionModel().clearSelection();
	}
}
