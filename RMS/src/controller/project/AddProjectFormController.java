package controller.project;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddProjectFormController {
	@FXML TextField projectNameId;
	@FXML TextField numOfHumansId;
	@FXML TextField numOfModulesId;
	@FXML TextField technologyNameId;
	@FXML TextField goalOfTechId;
	@FXML Button addTechnologyButtonId;
	@FXML Button addProjectButtonId;
	
	@FXML public void handleAddTechnologyButton(){
		
		System.out.println("handleAddTechnologyButton");
		
	}
	
	@FXML public void handleaddProjectButton(){
			
		System.out.println("handleAddProjectButton");
	}
	
	
	
	
}
