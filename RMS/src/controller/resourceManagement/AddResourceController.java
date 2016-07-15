package controller.resourceManagement;

import java.io.IOException;
import java.util.Map;

import controller.ErrorMessage;
import controller.SuccessMessage;
import controller.resourceAllocation.AddRequiredResourceController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.organization.resource.FinancialResourceCreator;
import logic.organization.resource.HumanResourceCreator;
import logic.organization.resource.InformationResourceCreator;
import logic.organization.resource.PhysicalResourceCreator;
import logic.organization.unit.Unit;

public class AddResourceController {
	@FXML
	private Button returnButton;
	@FXML
	private ListView<String> unitList;
	@FXML
	private ComboBox resourceTypeCombo;
	@FXML
	private TextField resourceId;

	@FXML
	private Label firstLabel;
	@FXML
	private Label secondLabel;
	@FXML
	private TextField firstInput;
	@FXML
	private TextField secondInput;
	@FXML
	private Text message;

	private Map<String, Long> units;
	private String returnState;
	
	public AddResourceController() {
		// TODO Auto-generated constructor stub
		message = new Text();
	}

	@FXML
	private void handleResourceTypeCombo() {
		String value = (String) resourceTypeCombo.getValue();
		if(value.equals(ResourceType.FINANCIALRESOURCE.getFarsiType())) {
			firstLabel.setText("میزان بودجه");
			secondLabel.setVisible(false);
			secondInput.setVisible(false);
		} 
		else if (value.equals(ResourceType.INFORMATIONALRESOURCE.getFarsiType()) || value.equals(ResourceType.PHYSICALRESOURCE.getFarsiType())) {
			firstLabel.setText("نوع");
			secondLabel.setVisible(false);
			secondInput.setVisible(false);
		}
		else if (value.equals(ResourceType.HUMANRESOUCE.getFarsiType())) {
			firstLabel.setText("نام");
			secondLabel.setText("نام خانوادگی");
			secondLabel.setVisible(true);
			secondInput.setVisible(true);
		}

	}

	@FXML
	private void handleReturnButton() {
		try {
			Stage stage;
			Parent root;
			stage = (Stage) returnButton.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(returnState));
			root = (Parent) loader.load();

			Scene scene = new Scene(root);
			stage.setScene(scene);
			if (returnState.equals("/view/resourceAllocation/AddRequiredResourceForm.fxml")) {
				AddRequiredResourceController addRequiredResourceController = loader.<AddRequiredResourceController> getController();
				addRequiredResourceController.initial();
			}
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	private void handleAddButton() {
		String resourceType = (String) this.resourceTypeCombo.getValue();
		String resourceId = this.resourceId.getText();
		String firstValue = this.firstInput.getText();
		String secondValue = this.secondInput.getText();
		String unit = this.unitList.getSelectionModel().getSelectedItem();
		Long unitId = -1l;
		if (unit != null) {
			unitId = units.get(unit);
		}
		if(resourceType == null || resourceId == null || firstValue == null || (resourceType.equals(ResourceType.HUMANRESOUCE.getFarsiType()) && secondValue == null))  {
			message.setFill(Color.RED);
			message.setText(ErrorMessage.INVALID_INPUT);
		}
		else {
			if(resourceType.equals(ResourceType.FINANCIALRESOURCE.getFarsiType())) {
				double amount = Double.parseDouble(firstValue);
				new FinancialResourceCreator().createFinancialResource(unitId, resourceId, amount);
			} 
			else if (resourceType.equals(ResourceType.INFORMATIONALRESOURCE.getFarsiType())) {
				new InformationResourceCreator().createInformationalResource(unitId, resourceId, firstValue);
			}
			else if (resourceType.equals(ResourceType.PHYSICALRESOURCE.getFarsiType())) {

				new PhysicalResourceCreator().createPhysicalResource(unitId, resourceId, firstValue);
			}
			else if (resourceType.equals(ResourceType.HUMANRESOUCE.getFarsiType())) {
				new HumanResourceCreator().createHumanResource(unitId, resourceId, firstValue, secondValue);
			}
			
			message.setText(SuccessMessage.ADD_INFO);
			message.setFill(Color.GREEN);
		}
		this.clear();
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
		resourceTypeCombo.setItems(types);
	}
	
	private void clear() {
		this.resourceId.clear();
		this.firstInput.clear();
		this.secondInput.clear();
		
		this.unitList.getSelectionModel().clearSelection();
	}

	public void initial() {
		this.showResourceTypes();
		this.showUnitsList();
	}

	public String getReturnState() {
		return returnState;
	}

	public void setReturnState(String returnState) {
		this.returnState = returnState;
	}

}
