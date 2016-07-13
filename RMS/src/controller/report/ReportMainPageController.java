package controller.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import controller.resourceManagement.ResourceType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import logic.organization.project.Project;
import logic.organization.resource.Resource;

public class ReportMainPageController {
	@FXML Button existingResourcesReportButton;
	@FXML Button CirculationOfResourcesReportButton;
	@FXML Button requiredResourcesReportButton;
	@FXML private TreeView<String> existingResourcesListTree; 

	@FXML public void handleExistingResourcesButton(){
		existingResourcesListTree.setRoot(null);
		buildAndShowExistingResourcesTree();
	}

	@FXML public void handleCirculationOfResourcesButton(){
		
	}

	@FXML public void handleRequiredResourcesButton(){
		existingResourcesListTree.setRoot(null);
		buildAndShowRequiredResources();
	}
	
	private void buildAndShowExistingResourcesTree(){
		TreeItem<String> existingResourcesListRoot = new TreeItem<String> ("منابع سازمان");
		existingResourcesListRoot.setExpanded(true);

		TreeItem<String> financialRootItem = createARootForExistingResourcesReport(ResourceType.FINANCIALRESOURCE);
		existingResourcesListRoot.getChildren().add(financialRootItem);
		financialRootItem.setExpanded(true);
		
		TreeItem<String> informationRootItem = createARootForExistingResourcesReport(ResourceType.INFORMATIONALRESOURCE);
		existingResourcesListRoot.getChildren().add(informationRootItem);
		informationRootItem.setExpanded(true);
		
		TreeItem<String> humanRootItem = createARootForExistingResourcesReport(ResourceType.HUMANRESOUCE);
		existingResourcesListRoot.getChildren().add(humanRootItem);
		humanRootItem.setExpanded(true);
		
		TreeItem<String> physicalRootItem = createARootForExistingResourcesReport(ResourceType.PHYSICALRESOURCE);
		existingResourcesListRoot.getChildren().add(physicalRootItem);
		physicalRootItem.setExpanded(true);

		this.existingResourcesListTree.setRoot(existingResourcesListRoot);
	}
	
	public void buildAndShowRequiredResources(){
		TreeItem<String> existingResourcesListRoot = new TreeItem<String> ("پروژه‌های سازمان");
		existingResourcesListRoot.setExpanded(true);

		Project project = new Project();
		Map<String, List<String>> financialResources = project.getFinancialResourcesUsedInProjects();
		Map<String, List<String>> informationalResources = project.getInformationalResourcesUsedInProjects();
//		Map<String, List<String>> humanResources = project.getHumanResourcesUsedInProjects();
//		Map<String, List<String>> physicalResources = project.getPhysicalResourcesUsedInProjects();
		

		Set<String> frKeySet = financialResources.keySet();
		for(String key: frKeySet){
			TreeItem<String> rootItem = new TreeItem<String> (key);
			rootItem.setExpanded(true);
			
			TreeItem<String> financialRootItem = addATypeOfResource(rootItem, ResourceType.FINANCIALRESOURCE);
			financialRootItem.getChildren().addAll(createOrdinaryTreeItems(financialResources.get(key)));
			
			TreeItem<String> informationalRootItem = addATypeOfResource(rootItem, ResourceType.INFORMATIONALRESOURCE);
			informationalRootItem.getChildren().addAll(createOrdinaryTreeItems(informationalResources.get(key)));
			
			existingResourcesListRoot.getChildren().add(rootItem);
			this.existingResourcesListTree.setRoot(existingResourcesListRoot);
		}
	}
	
	
	private TreeItem<String> addATypeOfResource(TreeItem<String> root, ResourceType resourceType){
		TreeItem<String> subRootItem = new TreeItem<String> (resourceType.getFarsiType());;
		subRootItem.setExpanded(true);
		root.getChildren().add(subRootItem);	
		return subRootItem;
	}
	
	
	private List<TreeItem<String>> createTreeItemsForUnitInformationAndCounts(List<String> unitInformation, List<String> unitCount) {
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
	
	private TreeItem<String> createARootForExistingResourcesReport(ResourceType type){
		TreeItem<String> rootItem = new TreeItem<String> (type.getFarsiType());
		rootItem.setExpanded(true);
		List<String> financialResourcesUnitsInformation = Resource.getResourcesUnits(type.getEnglishType());
		List<String> financialResourcesUnitsCount = Resource.getResourcesUnitCounts(type.getEnglishType());
		rootItem.getChildren().addAll(createTreeItemsForUnitInformationAndCounts(financialResourcesUnitsInformation, financialResourcesUnitsCount));
		return rootItem;
	}
	
	private List<TreeItem<String>> createOrdinaryTreeItems(List<String> elements) {
		List<TreeItem<String>> children = new ArrayList<TreeItem<String>>();
		for (String element : elements) {
			TreeItem<String> item = new TreeItem<String> (element);
			children.add(item);
		}
		
		return children;
	}
}
