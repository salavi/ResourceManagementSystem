package controller.report;

import java.util.ArrayList;
import java.util.List;

import controller.resourceManagement.ResourceType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import logic.organization.resource.Resource;

public class ReportMainPageController {
	@FXML Button existingResourcesReportButton;
	@FXML Button CirculationOfResourcesReportButton;
	@FXML Button requiredResourcesReportButton;
	@FXML private TreeView<String> existingResourcesListTree; 

	@FXML public void handleExistingResourcesButton(){
		buildAndShowTheTree();
	}

	@FXML public void handleCirculationOfResourcesButton(){

	}

	@FXML public void handleRequiredResourcesButton(){

	}
	
	private void buildAndShowTheTree(){
		TreeItem<String> existingResourcesListRoot = new TreeItem<String> ("منابع سازمان");
		existingResourcesListRoot.setExpanded(true);

		TreeItem<String> financialRootItem = createARoot(ResourceType.FINANCIALRESOURCE);
		existingResourcesListRoot.getChildren().add(financialRootItem);
		financialRootItem.setExpanded(true);
		
		TreeItem<String> informationRootItem = createARoot(ResourceType.INFORMATIONALRESOURCE);
		existingResourcesListRoot.getChildren().add(informationRootItem);
		informationRootItem.setExpanded(true);
		
		TreeItem<String> humanRootItem = createARoot(ResourceType.HUMANRESOUCE);
		existingResourcesListRoot.getChildren().add(humanRootItem);
		humanRootItem.setExpanded(true);
		
		TreeItem<String> physicalRootItem = createARoot(ResourceType.PHYSICALRESOURCE);
		existingResourcesListRoot.getChildren().add(physicalRootItem);
		physicalRootItem.setExpanded(true);

		this.existingResourcesListTree.setRoot(existingResourcesListRoot);
	}
	
	private List<TreeItem<String>> createTreeItems(List<String> unitInformation, List<String> unitCount) {
		try{
			List<TreeItem<String>> children = new ArrayList<>();
			int arraySize = unitInformation.size();
			String array[] = new String[arraySize];
			int counter = 0;
			for (String information : unitInformation) {
				array[counter] = information;
				counter++;
			}
			counter = 0;
			for(String count : unitCount){
				array[counter] += " " + count;
				counter++;
			}
			for(int i = 0; i < arraySize; i++){
				TreeItem<String> element = new TreeItem<String> (array[i]);
				children.add(element);
			}
			return children;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	private TreeItem<String> createARoot(ResourceType type){
		TreeItem<String> rootItem = new TreeItem<String> (type.getFarsi_Type());
		rootItem.setExpanded(true);
		List<String> financialResourcesUnitsInformation = Resource.getResourcesUnits(type.getEnglish_type());
		List<String> financialResourcesUnitsCount = Resource.getResourcesUnitCounts(type.getEnglish_type());
		rootItem.getChildren().addAll(createTreeItems(financialResourcesUnitsInformation, financialResourcesUnitsCount));
		return rootItem;
	}
}
