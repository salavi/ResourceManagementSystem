package controller.project;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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

abstract class AddProcessController {

	@FXML
	protected Button returnButton;
	@FXML
	protected Button addButton;
	@FXML
	protected Button addResourceButton;
	@FXML
	protected Button addProjectButton;
	@FXML
	protected ListView<String> projectList;
	@FXML
	protected ListView<String> unitList;
	@FXML
	protected ListView<String> resourceList;
	@FXML
	protected ListView<String> selectedResourceList;
	@FXML
	protected TextField activityNameInput;
	@FXML
	protected DatePicker startDateInput;
	@FXML
	protected DatePicker endDateInput;
	@FXML
	protected ComboBox<String> resourceTypeCombo;
	
	protected Map<String, Long> units;
	protected Map<String, Long> projects;
	private Map<String, Long> humanResources;
	private Map<String, Long> financialResources;
	private Map<String, Long> informationalResources;
	private Map<String, Long> physicalResources;
	protected Set<Long> selectedResourcesIds = new TreeSet<Long>();

	public AddProcessController() {
		super();
	}
	
	@FXML
	abstract void handleAddButton();
	
	@FXML
	protected void handleReturnButton() {
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
	protected void handleAddResourceButton() {
		String resourceType = resourceTypeCombo.getValue();
		ObservableList<String> items = resourceList.getSelectionModel().getSelectedItems();
		selectedResourceList.getItems().addAll(items);
		Long resourceId;
		if(resourceType.equals(ResourceType.FINANCIALRESOURCE.getFarsiType())) {
			for (String item : items) {	
				resourceId = financialResources.get(item);
				selectedResourcesIds.add(resourceId);
			}
		} 
		else if (resourceType.equals(ResourceType.INFORMATIONALRESOURCE.getFarsiType())) {
			for (String item : items) {	
				resourceId = informationalResources.get(item);
				selectedResourcesIds.add(resourceId);
			}
		}
		else if (resourceType.equals(ResourceType.PHYSICALRESOURCE.getFarsiType())) {
			for (String item : items) {	
				resourceId = physicalResources.get(item);
				selectedResourcesIds.add(resourceId);
			}
		}
		else if (resourceType.equals(ResourceType.HUMANRESOUCE.getFarsiType())) {
			for (String item : items) {	
				resourceId = humanResources.get(item);
				selectedResourcesIds.add(resourceId);
			}
		}
	}
	
	@FXML
	protected abstract void handleAddProjectButton();

	@FXML
	protected void handleResourceTypeCombo() {
		String value = (String) resourceTypeCombo.getValue();
		ObservableList<String> types = null;
		if(value.equals(ResourceType.FINANCIALRESOURCE.getFarsiType())) {
			financialResources = new FinancialResourceCreator().getAllFinancialResources();
			types = FXCollections.observableArrayList(financialResources.keySet());
			
		} 
		else if (value.equals(ResourceType.INFORMATIONALRESOURCE.getFarsiType())) {
			informationalResources = new InformationResourceCreator().getAllInformationalResources();
			types = FXCollections.observableArrayList(informationalResources.keySet());
		}
		else if (value.equals(ResourceType.PHYSICALRESOURCE.getFarsiType())) {
			physicalResources = new PhysicalResourceCreator().getAllPhysicalResources();
			types = FXCollections.observableArrayList(physicalResources.keySet());
		}
		else if (value.equals(ResourceType.HUMANRESOUCE.getFarsiType())) {
			humanResources = new HumanResourceCreator().getAllHumanResources();
			types = FXCollections.observableArrayList(humanResources.keySet());
		}
		resourceList.setItems(types);
		resourceList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}

	public void initial() {
		this.showResourceTypes();
		this.showUnitList();
		this.showProjectList();
	}
	
	protected void clear() {
		this.activityNameInput.clear();
		this.startDateInput.getEditor().clear();
		this.endDateInput.getEditor().clear();
		
		this.unitList.getSelectionModel().clearSelection();
		this.projectList.getSelectionModel().clearSelection();
		this.resourceList.getSelectionModel().clearSelection();
		this.resourceList.getItems().clear();
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
		ObservableList<String> items = FXCollections.observableArrayList(projects.keySet());
		projectList.setItems(items);
	}
	
}