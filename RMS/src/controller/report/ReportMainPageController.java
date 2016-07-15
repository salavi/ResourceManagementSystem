package controller.report;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import controller.resourceManagement.ResourceType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import logic.organization.project.Project;
import logic.organization.resource.Resource;

public class ReportMainPageController {
	@FXML Button existingResourcesReportButton;
	@FXML Button circulationOfResourcesReportButton;
	@FXML Button requiredResourcesReportButton;
	@FXML private TreeView<String> mainListTree; 

	@FXML public void handleExistingResourcesButton(){
		mainListTree.setRoot(null);
		buildAndShowExistingResourcesTree();
	}

	@FXML public void handleCirculationOfResourcesButton(){
		
	}

	@FXML public void handleRequiredResourcesButton(){
		mainListTree.setRoot(null);
		buildAndShowRequiredResourcesTree();
	}
	
	
	private void buildAndShowExistingResourcesTree(){
		TreeItem<String> existingResourcesListRoot = new TreeItem<String> ("منابع سازمان");
		existingResourcesListRoot.setExpanded(true);

		createATypeOfResourceRootForExistingResourcesReport(ResourceType.FINANCIALRESOURCE, existingResourcesListRoot);
		createATypeOfResourceRootForExistingResourcesReport(ResourceType.INFORMATIONALRESOURCE, existingResourcesListRoot);
		createATypeOfResourceRootForExistingResourcesReport(ResourceType.HUMANRESOUCE, existingResourcesListRoot);
		createATypeOfResourceRootForExistingResourcesReport(ResourceType.PHYSICALRESOURCE, existingResourcesListRoot);

		this.mainListTree.setRoot(existingResourcesListRoot);
	}
	
	
	public void buildAndShowRequiredResourcesTree(){
		TreeItem<String> projectsListRoot = new TreeItem<String> ("پروژه‌های سازمان");
		projectsListRoot.setExpanded(true);

		Project project = new Project();
		List<String> allProjectNames = project.getAllProjectNamesUsedInResourceUsageHistory();
		Map<String, List<String>> financialResources = project.getAResourceTypeUsedInProjects(ResourceType.FINANCIALRESOURCE.getEnglishType());
		Map<String, List<String>> informationalResources = project.getAResourceTypeUsedInProjects(ResourceType.INFORMATIONALRESOURCE.getEnglishType());
		Map<String, List<String>> humanResources = project.getAResourceTypeUsedInProjects(ResourceType.HUMANRESOUCE.getEnglishType());
		Map<String, List<String>> physicalResources = project.getAResourceTypeUsedInProjects(ResourceType.PHYSICALRESOURCE.getEnglishType());
		
		for(String aProject: allProjectNames){
			TreeItem<String> aProjectRoot = new TreeItem<String> (aProject);
			aProjectRoot.setExpanded(true);
			
			addATypeOfResourceForAProjectInRequiredResourcesReport(aProjectRoot, ResourceType.FINANCIALRESOURCE, financialResources);
			addATypeOfResourceForAProjectInRequiredResourcesReport(aProjectRoot, ResourceType.INFORMATIONALRESOURCE, informationalResources);
			addATypeOfResourceForAProjectInRequiredResourcesReport(aProjectRoot, ResourceType.HUMANRESOUCE, humanResources);
			addATypeOfResourceForAProjectInRequiredResourcesReport(aProjectRoot, ResourceType.PHYSICALRESOURCE, physicalResources);
			
			projectsListRoot.getChildren().add(aProjectRoot);
		}
		this.mainListTree.setRoot(projectsListRoot);
	}
	
	
	private void addATypeOfResourceForAProjectInRequiredResourcesReport(TreeItem<String> aProjectRoot, ResourceType resourceType, Map<String, List<String>> resourcesUsedInProjects){
		if (resourcesUsedInProjects == null || !resourcesUsedInProjects.containsKey(aProjectRoot.getValue()))
			return;
		TreeItem<String> typeOfResourceRoot = new TreeItem<String> (resourceType.getFarsiType());;
		typeOfResourceRoot.setExpanded(true);
		typeOfResourceRoot.getChildren().addAll(createOrdinaryTreeItems(resourcesUsedInProjects.get(aProjectRoot.getValue())));
		aProjectRoot.getChildren().add(typeOfResourceRoot);	
	}
	
	private void createATypeOfResourceRootForExistingResourcesReport(ResourceType type, TreeItem<String> existingResourcesListRoot){
		TreeItem<String> rootItem = new TreeItem<String> (type.getFarsiType());
		rootItem.setExpanded(true);
		List<String> unitsOfResources = Resource.findExistingResourcesInUnits(type.getEnglishType());
		if(unitsOfResources == null)
			return;
		rootItem.getChildren().addAll(createOrdinaryTreeItems(unitsOfResources));
		existingResourcesListRoot.getChildren().add(rootItem);
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
