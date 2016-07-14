package controller.prediction;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
	public void handleSearchResourceButton() {
		System.out.println("searchResource button");
		int minNumOfHumans = Integer.parseInt(this.minNumOfHumansId.getText());
		int maxNumOfHumans = Integer.parseInt(this.maxNumOfHumansId.getText());
		int minNumOfModules = Integer.parseInt(this.minNumOfModulesId.getText());
		int maxNumOfModules = Integer.parseInt(this.maxNumOfModulesId.getText());
		String[] technologies = partseTechnologyList(technologiesListId.getText());
		Project project = new Project();
		List<Long> projectModels = project.findSimilarProjects(minNumOfHumans, maxNumOfHumans, minNumOfModules,
				maxNumOfModules, technologies);
		
		for(Long projectModel: projectModels){
			System.out.println(projectModel);
		}
		// ModulesNum humansNum
	}
	
	@FXML
	public void handleRequirementsPrediction(){
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
