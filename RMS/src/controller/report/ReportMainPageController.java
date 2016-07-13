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
	
	public void buildAndShowRequiredResources(){
		TreeItem<String> existingResourcesListRoot = new TreeItem<String> ("پروژه‌های سازمان");
		existingResourcesListRoot.setExpanded(true);

		Project project = new Project();
		Map<String, List<String>> financialResources = project.getFinancialResourcesUsedInProjects();
		
		Set<String> frKeySet = financialResources.keySet();
		for(String key: frKeySet){
			TreeItem<String> rootItem = new TreeItem<String> (key);
			rootItem.setExpanded(true);
			rootItem.getChildren().addAll(createTreeItems(financialResources.get(key)));
			existingResourcesListRoot.getChildren().add(rootItem);
			this.existingResourcesListTree.setRoot(existingResourcesListRoot);
		}
//		TreeItem<String> financialRootItem = new TreeItem<String> (ResourceType.FINANCIALRESOURCE.getFarsiType());
//		financialRootItem.setExpanded(true);
////		ProjectAdapter projectAdapter = new ProjectAdapter();
////		List<String> financialResourcesUnitsInProjects = projectAdapter.getFinancialResourcesUsedInProjects();
////		financialRootItem.getChildren().addAll(createTreeItems(financialResourcesUnitsInProjects));
		
//		existingResourcesListRoot.getChildren().add(financialRootItem);
//		financialRootItem.setExpanded(true);
//		
//		this.existingResourcesListTree.setRoot(existingResourcesListRoot);
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
		TreeItem<String> rootItem = new TreeItem<String> (type.getFarsiType());
		rootItem.setExpanded(true);
		List<String> financialResourcesUnitsInformation = Resource.getResourcesUnits(type.getEnglishType());
		List<String> financialResourcesUnitsCount = Resource.getResourcesUnitCounts(type.getEnglishType());
		rootItem.getChildren().addAll(createTreeItems(financialResourcesUnitsInformation, financialResourcesUnitsCount));
		return rootItem;
	}
	
	private List<TreeItem<String>> createTreeItems(List<String> elements) {
		List<TreeItem<String>> children = new ArrayList<TreeItem<String>>();
		for (String element : elements) {
			TreeItem<String> item = new TreeItem<String> (element);
			children.add(item);
		}
		
		return children;
	}
}
