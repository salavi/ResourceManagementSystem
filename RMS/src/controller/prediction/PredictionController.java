package controller.prediction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import controller.resourceManagement.ResourceType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import logic.organization.project.Project;
import model.organization.project.ProjectModel;

public class PredictionController {

	@FXML
	private TextField minNumOfHumansId;
	@FXML
	private TextField maxNumOfHumansId;
	@FXML
	private TextField minNumOfModulesId;
	@FXML
	private TextField maxNumOfModulesId;
	@FXML
	private TextField technologiesListId;
	@FXML
	private Button searchResourceButtonId;
	@FXML
	private Button requirementsPredictionButtonId;
	@FXML
	private TreeView predictionTreeId;

	@FXML
	public void handleSearchResourceButton() {
		System.out.println("searchResource button");
		int minNumOfHumans = Integer.parseInt(this.minNumOfHumansId.getText());
		int maxNumOfHumans = Integer.parseInt(this.maxNumOfHumansId.getText());
		int minNumOfModules = Integer.parseInt(this.minNumOfModulesId.getText());
		int maxNumOfModules = Integer.parseInt(this.maxNumOfModulesId.getText());
		String[] technologies = partseTechnologyList(technologiesListId.getText());
		Project project = new Project();
		Map<String, List<String>> projectNameHumanResourcesMap = project.findSimilarProjectsHumanResources(
				minNumOfHumans, maxNumOfHumans, minNumOfModules, maxNumOfModules, technologies);

		Map<String, List<String>> projectNameInformationalResourceMap = project
				.findSimilarProjectsInformationalResources(minNumOfHumans, maxNumOfHumans, minNumOfModules,
						maxNumOfModules, technologies);

		Map<String, List<String>> projectNamePhysicalResourceMap = project.findSimilarProjectsPhysicalResources(
				minNumOfHumans, maxNumOfHumans, minNumOfModules, maxNumOfModules, technologies);

		Map<String, List<String>> projectNameFinancialResourceMap = project.findSimilarProjectsFinancialResources(
				minNumOfHumans, maxNumOfHumans, minNumOfModules, maxNumOfModules, technologies);

		showSimilarProjectResources(projectNameFinancialResourceMap, projectNameHumanResourcesMap,
				projectNameInformationalResourceMap, projectNamePhysicalResourceMap);
	}

	private void showSimilarProjectResources(Map<String, List<String>> projectNameFinancialResourceMap,
			Map<String, List<String>> projectNameHumanResourcesMap,
			Map<String, List<String>> projectNameInformationalResourceMap,
			Map<String, List<String>> projectNamePhysicalResourceMap) {

		TreeItem<String> projectResourceRoot = new TreeItem<String>("منابع پروژه‌های مشابه");
		projectResourceRoot.setExpanded(true);

		Set<String> setOfAllProjects = getSetOfAllProjects(projectNameFinancialResourceMap.keySet(),
				projectNameHumanResourcesMap.keySet(), projectNameInformationalResourceMap.keySet(),
				projectNamePhysicalResourceMap.keySet());

		for (String project : setOfAllProjects) {
			TreeItem<String> projectRoot = new TreeItem<String>(project);
			projectRoot.setExpanded(true);
			TreeItem<String> financialRoot = new TreeItem<String>(ResourceType.FINANCIALRESOURCE.getFarsiType());
			if (projectNameFinancialResourceMap.containsKey(project)) {
				financialRoot.setExpanded(true);
				financialRoot.getChildren().addAll(createTreeItems(projectNameFinancialResourceMap.get(project)));
			}
			TreeItem<String> humanRoot = new TreeItem<String>(ResourceType.HUMANRESOUCE.getFarsiType());
			if (projectNameHumanResourcesMap.containsKey(project)) {
				humanRoot.setExpanded(true);
				humanRoot.getChildren().addAll(createTreeItems(projectNameHumanResourcesMap.get(project)));
			}
			TreeItem<String> informationRoot = new TreeItem<String>(ResourceType.INFORMATIONALRESOURCE.getFarsiType());
			if (projectNameInformationalResourceMap.containsKey(project)) {
				informationRoot.setExpanded(true);
				informationRoot.getChildren().addAll(createTreeItems(projectNameInformationalResourceMap.get(project)));
			}
			TreeItem<String> physicalRoot = new TreeItem<String>(ResourceType.PHYSICALRESOURCE.getFarsiType());
			if (projectNamePhysicalResourceMap.containsKey(project)) {
				physicalRoot.setExpanded(true);
				physicalRoot.getChildren().addAll(createTreeItems(projectNamePhysicalResourceMap.get(project)));
			}
			projectRoot.getChildren().add(financialRoot);
			projectRoot.getChildren().add(humanRoot);
			projectRoot.getChildren().add(informationRoot);
			projectRoot.getChildren().add(physicalRoot);
			projectResourceRoot.getChildren().add(projectRoot);
		}
		this.predictionTreeId.setRoot(projectResourceRoot);
	}

	private List<TreeItem<String>> createTreeItems(List<String> keys) {
		List<TreeItem<String>> children = new ArrayList<TreeItem<String>>();
		for (String key : keys) {
			TreeItem<String> item = new TreeItem<String>(key);
			children.add(item);
		}

		return children;
	}

	private Set<String> getSetOfAllProjects(Set<String> set, Set<String> set2, Set<String> set3, Set<String> set4) {

		Set<String> setOfAllProjects = new TreeSet<>();
		setOfAllProjects.addAll(set);
		setOfAllProjects.addAll(set2);
		setOfAllProjects.addAll(set3);
		setOfAllProjects.addAll(set4);
		return setOfAllProjects;
	}

	@FXML
	public void handleRequirementsPrediction() {
		System.out.println("RequirementsPrediction");
	}

	private String[] partseTechnologyList(String text) {
		String[] techs = text.split(",");
		for (int i = 0; i < techs.length; i++) {
			techs[i] = techs[i].trim();
		}
		return techs;
	}

}
