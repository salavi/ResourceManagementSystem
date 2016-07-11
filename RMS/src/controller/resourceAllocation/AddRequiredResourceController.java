package controller.resourceAllocation;

import java.util.Map;

import controller.resourceManagement.ResourceType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import logic.organization.resource.FinancialResourceCreator;
import logic.organization.resource.HumanResourceCreator;
import logic.organization.resource.InformationResourceCreator;
import logic.organization.resource.PhysicalResourceCreator;
import logic.organization.unit.Unit;
import model.organization.resource.InformationalResourceModel;

public class AddRequiredResourceController {

	@FXML
	private ListView<String> unitList;
	@FXML
	private ListView<String> resourceList;
	@FXML
	private ComboBox resourceTypeComboId;
	
	private Map<String, Long> units;
	private Map<String, Long> financialResources;
	private Map<String, Long> informationalResources;
	private Map<String, Long> humanResources;
	private Map<String, Long> physicalResources;
	
	
	@FXML
	public void handleResourceTypeCombo(){
		String value = (String) resourceTypeComboId.getValue();
		if(value.equals(ResourceType.FINANCIALRESOURCE.getType())) {
			financialResources = new FinancialResourceCreator().getAllFinancialResources();
			showResourcesList(financialResources);
		} 
		else if (value.equals(ResourceType.INFORMATIONALRESOURCE.getType()) || value.equals(ResourceType.PHYSICALRESOURCE.getType())) {
			informationalResources = new InformationResourceCreator().getAllInformationalResources();
			showResourcesList(informationalResources);
		}
		else if (value.equals(ResourceType.HUMANRESOUCE.getType())) {
			humanResources = new HumanResourceCreator().getAllHumanResources();
			showResourcesList(humanResources);
		}else if (value.equals(ResourceType.PHYSICALRESOURCE.getType())){
			physicalResources = new PhysicalResourceCreator().getAllPhysicalResources();
			showResourcesList(physicalResources);
		}
		
	}
	
	private void showResourcesList(Map<String, Long> resource){
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
		ObservableList<String> types = FXCollections.observableArrayList(
				"منبع مالی",
				"منبع انسانی",
				"منبع فیزیکی",
				"منبع اطلاعاتی"
				);
		resourceTypeComboId.setItems(types);
	}
	
	public void initial() {
		this.showResourceTypes();
		this.showUnitsList();
	}
}
