package controller.resourceAllocation;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

import controller.ErrorMessage;
import controller.SuccessMessage;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.organization.activity.Activity;
import logic.organization.project.Project;
import logic.organization.resource.FinancialResourceCreator;
import logic.organization.resource.HumanResourceCreator;
import logic.organization.resource.InformationResourceCreator;
import logic.organization.resource.PhysicalResourceCreator;
import logic.organization.resourceUsage.ResourceUsageHistory;
import logic.organization.unit.Unit;

public class AddAllocatedResourceController {
	@FXML
	private ComboBox<String> resourceTypeCombo;
	@FXML
	private ComboBox<String> projectCombo;
	@FXML
	private ListView<String> resourceList;
	@FXML
	private ListView<String> activityList;
	@FXML
	private DatePicker startDateInput;
	@FXML
	private DatePicker endDateInput;
	@FXML
	private Button addButton;
	@FXML
	private Button returnButton;
	@FXML
	private Button addResourceButton;
	@FXML
	private Text message;

	private Map<String, Long> projects;
	private Map<String, Long> humanResources;
	private Map<String, Long> financialResources;
	private Map<String, Long> informationalResources;
	private Map<String, Long> physicalResources;
	private Map<String, Long> activities;

	@FXML
	private void handleAddResourceButton() {

	}

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
		LocalDate startDate = startDateInput.getValue();
		LocalDate endDate = endDateInput.getValue();
		
		String resourceType = resourceTypeCombo.getValue();
		String resource = resourceList.getSelectionModel().getSelectedItem();
		String project = projectCombo.getValue();
		String activity = activityList.getSelectionModel().getSelectedItem();
		if (!isInputValid(startDate, endDate, resourceType, resource, project, activity))  {
			message.setFill(Color.RED);
			message.setText(ErrorMessage.INVALID_INPUT);
		}
		else {
			Long resourceId = getResourceId(resourceType, resource);			
			Long projectId = projects.get(project);			
			Long activityId = activities.get(activity);
			new ResourceUsageHistory().createRUH(activityId, projectId, resourceId);
			
			message.setText(SuccessMessage.ADD_INFO);
			message.setFill(Color.GREEN);
		}
		this.clear();
	}

	private boolean isInputValid(LocalDate startDate, LocalDate endDate, String resourceType, String resource,
			String project, String activity) {
		return startDate != null && endDate != null && resourceType != null && resource != null && project != null && activity != null;
	}

	@FXML
	protected void handleResourceTypeCombo() {
		System.out.println("resourceTypeCombo");
		String value = (String) resourceTypeCombo.getValue();
		ObservableList<String> types = null;
		if (value.equals(ResourceType.FINANCIALRESOURCE.getFarsiType())) {
			financialResources = new FinancialResourceCreator().getAllFinancialResources();
			types = FXCollections.observableArrayList(financialResources.keySet());

		} else if (value.equals(ResourceType.INFORMATIONALRESOURCE.getFarsiType())) {
			informationalResources = new InformationResourceCreator().getAllInformationalResources();
			types = FXCollections.observableArrayList(informationalResources.keySet());
		} else if (value.equals(ResourceType.PHYSICALRESOURCE.getFarsiType())) {
			physicalResources = new PhysicalResourceCreator().getAllPhysicalResources();
			types = FXCollections.observableArrayList(physicalResources.keySet());
		} else if (value.equals(ResourceType.HUMANRESOUCE.getFarsiType())) {
			humanResources = new HumanResourceCreator().getAllHumanResources();
			types = FXCollections.observableArrayList(humanResources.keySet());
		}
		resourceList.setItems(types);
	}

	@FXML
	private void handleProjectCombo() {
		String value = (String) projectCombo.getValue();
		Long projectId = -1l;
		if (value != null) {
			projectId = projects.get(value);
		}

		activities = new Activity().getActivitiesOfProject(projectId);
		ObservableList<String> items = FXCollections.observableArrayList(activities.keySet());
		activityList.setItems(items);
	}

	public void initial() {
		showProjectList();
		showResourceTypes();
	}

	private Long getResourceId(String resourceType, String resource) {
		if (resourceType.equals(ResourceType.FINANCIALRESOURCE.getFarsiType()))
			return financialResources.get(resource);
		else if (resourceType.equals(ResourceType.HUMANRESOUCE.getFarsiType()))
			return humanResources.get(resource);
		else if (resourceType.equals(ResourceType.INFORMATIONALRESOURCE.getFarsiType()))
			return informationalResources.get(resource);
		else if (resourceType.equals(ResourceType.PHYSICALRESOURCE.getFarsiType()))
			return physicalResources.get(resource);
		return -1l;
	}

	private void showResourceTypes() {
		ObservableList<String> types = FXCollections.observableArrayList("منبع مالی", "منبع انسانی", "منبع فیزیکی",
				"منبع اطلاعاتی");
		resourceTypeCombo.setItems(types);
	}

	private void showProjectList() {
		Project project = new Project();
		projects = project.getAllProjects();
		ObservableList<String> items = FXCollections.observableArrayList(projects.keySet());
		projectCombo.setItems(items);
	}

	private void clear() {
		this.resourceList.getSelectionModel().clearSelection();
		this.activityList.getSelectionModel().clearSelection();
		this.activityList.getSelectionModel().clearSelection();

		this.startDateInput.getEditor().clear();
		this.endDateInput.getEditor().clear();
	}
}
