package controller.resourceAllocation;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

import controller.ErrorMessage;
import controller.SuccessMessage;
import controller.resourceManagement.AddResourceController;
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
import logic.organization.resource.FinancialResourceCreator;
import logic.organization.resource.HumanResourceCreator;
import logic.organization.resource.InformationResourceCreator;
import logic.organization.resource.PhysicalResourceCreator;
import logic.organization.resource.Resource;
import logic.organization.unit.Unit;
import model.organization.resource.ResourceModel;

public class AddRequiredResourceController {

	@FXML
	private ListView<String> unitList;
	@FXML
	private ListView<String> resourceList;
	@FXML
	private ComboBox resourceTypeComboId;
	@FXML
	private ComboBox requirementStatusComboId;
	@FXML
	private Button addRequiredResourceButtonId;
	@FXML
	private Button returnButtonId;
	@FXML
	private Button addResourceButtonId;
	@FXML
	private DatePicker resolvingDatePickerId;
	@FXML
	private Text message;

	private Map<String, Long> units;
	private Map<String, Long> financialResources;
	private Map<String, Long> informationalResources;
	private Map<String, Long> humanResources;
	private Map<String, Long> physicalResources;
	
	public AddRequiredResourceController() {
		// TODO Auto-generated constructor stub
		message = new Text();
	}

	@FXML
	public void handleResourceTypeCombo() {
		String value = (String) resourceTypeComboId.getValue();
		if (value.equals(ResourceType.FINANCIALRESOURCE.getFarsiType())) {
			financialResources = new FinancialResourceCreator().getAllFinancialResources();
			showResourcesList(financialResources);
		} else if (value.equals(ResourceType.INFORMATIONALRESOURCE.getFarsiType())
				|| value.equals(ResourceType.PHYSICALRESOURCE.getFarsiType())) {
			informationalResources = new InformationResourceCreator().getAllInformationalResources();
			showResourcesList(informationalResources);
		} else if (value.equals(ResourceType.HUMANRESOUCE.getFarsiType())) {
			humanResources = new HumanResourceCreator().getAllHumanResources();
			showResourcesList(humanResources);
		} else if (value.equals(ResourceType.PHYSICALRESOURCE.getFarsiType())) {
			physicalResources = new PhysicalResourceCreator().getAllPhysicalResources();
			showResourcesList(physicalResources);
		}

	}

	@FXML
	public void handleRequirementStatusCombo() {
		String value = (String) requirementStatusComboId.getValue();
		if (value.equals("رفع شده")) {
			resolvingDatePickerId.setDisable(false);
		} else if (value.equals("رفع نشده")) {
			resolvingDatePickerId.setDisable(true);
		}
	}

	@FXML
	public void handleAddResourceButton() {
		try {
			Stage stage;
			Parent root;
			stage = (Stage) addResourceButtonId.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/resourceManagement/AddResourceForm.fxml"));
			root = (Parent) loader.load();
			AddResourceController addResourceController = loader.<AddResourceController> getController();
			addResourceController.setReturnState("/view/resourceAllocation/AddRequiredResourceForm.fxml");
			Scene scene = new Scene(root);
			stage.setScene(scene);
			addResourceController.initial();
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void handleReturnButton() {
		try {
			Stage stage;
			Parent root;
			stage = (Stage) returnButtonId.getScene().getWindow();
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
	public void handleAddRequiredButton() {
		String resourceType = (String) this.resourceTypeComboId.getValue();
		String requirementStatus = (String) this.requirementStatusComboId.getValue();
		String resource = this.resourceList.getSelectionModel().getSelectedItem();
		String unit = this.unitList.getSelectionModel().getSelectedItem();
		LocalDate date = resolvingDatePickerId.getValue();
		Long unitId = -1l;
		Long resourceId = -1l;
		System.out.println();
		if (resourceType == null || requirementStatus == null || resource == null || unit == null) {
			message.setFill(Color.RED);
			message.setText(ErrorMessage.INVALID_INPUT);
		} 
		 else {
			unitId = units.get(unit);
			resourceId = findResourceId(resourceType, resource);
			Unit unitResource = new Unit();
			unitResource.addRequiredResourceToUnit(resourceId, unitId);
			
			message.setText(SuccessMessage.ADD_INFO);
			message.setFill(Color.GREEN);
		}
		
		this.clear();
	}

	private Long findResourceId(String resourceType, String resource) {
		if (resourceType.equals(ResourceType.FINANCIALRESOURCE.getFarsiType())) {
			return financialResources.get(resource);
		} else if (resourceType.equals(ResourceType.INFORMATIONALRESOURCE.getFarsiType())) {
			return informationalResources.get(resource);
		} else if (resourceType.equals(ResourceType.PHYSICALRESOURCE.getFarsiType())) {
			return physicalResources.get(resource);
		} else if (resourceType.equals(ResourceType.HUMANRESOUCE.getFarsiType())) {
			return humanResources.get(resource);
		}
		return null;
	}

	private void showResourcesList(Map<String, Long> resource) {
		ObservableList<String> items = FXCollections.observableArrayList(resource.keySet());
		resourceList.setItems(items);
	}

	private void showUnitsList() {
		Unit unit = new Unit();
		units = unit.getAllUnits();
		ObservableList<String> items = FXCollections.observableArrayList(units.keySet());
		unitList.setItems(items);

	}

	private void showResourceTypes() {
		ObservableList<String> types = FXCollections.observableArrayList("منبع مالی", "منبع انسانی", "منبع فیزیکی",
				"منبع اطلاعاتی");
		resourceTypeComboId.setItems(types);
	}

	private void showRequirementStatus() {
		ObservableList<String> requirementStatus = FXCollections.observableArrayList("رفع شده", "رفع نشده");
		requirementStatusComboId.setItems(requirementStatus);
	}
	
	private void clear() {
		this.resourceList.getSelectionModel().clearSelection();
		this.unitList.getSelectionModel().clearSelection();
		
		this.resolvingDatePickerId.getEditor().clear();
	}

	public void initial() {
		this.showResourceTypes();
		this.showRequirementStatus();
		this.showUnitsList();
	}

}
