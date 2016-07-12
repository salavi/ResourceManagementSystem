package controller.prediction;

import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import logic.organization.project.Technology;
import logic.organization.unit.Unit;

public class ResourcePredictionController {
	
	@FXML
	private ListView technologyList;
	@FXML
	private TextField numOfHumansId;
	@FXML
	private TextField numOfModulesId;
	@FXML
	private Button searchButonId;
	
	private Map<String, Long> technologies;
	
	
	@FXML
	public void handleSearchButton(){
		
	}


	public void showAllTechnologies() {
//		System.out.println("showAllTechnologies");
//		Technology technology = new Technology();
//		technologies = technology.getAllUnits();
//		ObservableList<String> items = FXCollections.observableArrayList(technology.keySet());
//		technologyList.setItems(items);
		
	}
	
	
	
}
