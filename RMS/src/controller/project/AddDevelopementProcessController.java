package controller.project;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import controller.resourceManagement.ResourceType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.organization.project.Project;
import logic.organization.resource.FinancialResourceCreator;
import logic.organization.resource.HumanResourceCreator;
import logic.organization.resource.InformationResourceCreator;
import logic.organization.resource.PhysicalResourceCreator;
import logic.organization.unit.Unit;

public class AddDevelopementProcessController {
	@FXML private Button returnButton;
	@FXML private Button addButton;
	
	@FXML private ListView<String> projectList;
	@FXML private ListView<String> unitList;
	@FXML private ListView<String> resourceList;
	
	@FXML private TextField activityNameInput;
	@FXML private TextField moduleNameInput;
	@FXML private TextField moduleIdInput;
	
	@FXML private DatePicker startDateInput;
	@FXML private DatePicker endDateInput;
	
	@FXML private ComboBox<String> resourceTypeCombo;
	
	private Map<String, Long> units;
	private Map<String, Long> projects;
	private Map<String, Long> humanResources;
	private Map<String, Long> financialResources;
	private Map<String, Long> informationalResources;
	private Map<String, Long> physicalResources;
	
	private List<Long> selectedResourcesIds = new ArrayList<Long>();
	
	@FXML
	private void handleReturnButton() {
		try {
			Stage stage;
			Parent root;
			stage = (Stage) returnButton.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/RootLayout.fxml"));
			root = (Parent) loader.load();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	private void handleAddButton() {
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
		System.out.println(activity + " " + moduleId + " " + moduleName + " " + startDate + " " + endDate + " " + unit + " " + project);
		new Project().addDevelopementProcess(projectId, unitId, activity, moduleName, moduleId, startDate, endDate);
		
	}
	
	@FXML
	private void handleAddResourceButton() {
		String resourceType = resourceTypeCombo.getValue();
		ObservableList<String> items = resourceList.getSelectionModel().getSelectedItems();
		Long resourceId;
		if(resourceType.equals(ResourceType.FINANCIALRESOURCE.getType())) {
			for (String item : items) {	
				resourceId = financialResources.get(item);
				selectedResourcesIds.add(resourceId);
			}
		} 
		else if (resourceType.equals(ResourceType.INFORMATIONALRESOURCE.getType())) {
			for (String item : items) {	
				resourceId = informationalResources.get(item);
				selectedResourcesIds.add(resourceId);
			}
		}
		else if (resourceType.equals(ResourceType.PHYSICALRESOURCE.getType())) {
			for (String item : items) {	
				resourceId = physicalResources.get(item);
				selectedResourcesIds.add(resourceId);
			}
		}
		else if (resourceType.equals(ResourceType.HUMANRESOUCE.getType())) {
			for (String item : items) {	
				resourceId = humanResources.get(item);
				selectedResourcesIds.add(resourceId);
			}
		}
		
	}
	
	@FXML
	private void handleResourceTypeCombo() {
		String value = (String) resourceTypeCombo.getValue();
		ObservableList<String> types = null;
		if(value.equals(ResourceType.FINANCIALRESOURCE.getType())) {
			financialResources = new FinancialResourceCreator().getAllFinancialResources();
			types = FXCollections.observableArrayList(financialResources.keySet());
			
		} 
		else if (value.equals(ResourceType.INFORMATIONALRESOURCE.getType())) {
			informationalResources = new InformationResourceCreator().getAllInformationalResources();
			types = FXCollections.observableArrayList(informationalResources.keySet());
		}
		else if (value.equals(ResourceType.PHYSICALRESOURCE.getType())) {
			physicalResources = new PhysicalResourceCreator().getAllPhysicalResources();
			types = FXCollections.observableArrayList(physicalResources.keySet());
		}
		else if (value.equals(ResourceType.HUMANRESOUCE.getType())) {
			humanResources = new HumanResourceCreator().getAllHumanResources();
			types = FXCollections.observableArrayList(humanResources.keySet());
		}
		resourceList.setItems(types);
		resourceList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}

	public void initial() {
		// TODO Auto-generated method stub
		this.showResourceTypes();
		this.showUnitList();
		this.showProjectList();
	}
	
	private void showResourceTypes() {
		ObservableList<String> types = FXCollections.observableArrayList(
				"منبع مالی",
				"منبع انسانی",
				"منبع فیزیکی",
				"منبع اطلاعاتی"
				);
		resourceTypeCombo.setItems(types);
	}
	
	private void showUnitList() {
		Unit unit = new Unit();
		units = unit.getAllUnits();
		ObservableList<String> items = FXCollections.observableArrayList(units.keySet());
		unitList.setItems(items);
	}
	
	private void showProjectList() {
		Project project = new Project();
		projects = project.getAllProjects();
		System.out.println(projects.keySet().size());
		ObservableList<String> items = FXCollections.observableArrayList(projects.keySet());
		projectList.setItems(items);
	}
	
}
