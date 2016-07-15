package controller.project;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import logic.organization.activity.Activity;

public class DevelopementProcessController {
	@FXML
	Button addDevProcessButton;
	@FXML
	Button addMainProcessButton;
	@FXML
	TreeView<String> activityListTree;
	
	@FXML private Label activityNameLabel;
	@FXML private Label startDateLabel;
	@FXML private Label endDateLabel;
	@FXML private Label projectNameLabel;
	@FXML private Label unitNameLabel;
	@FXML private Label moduleNameLabel;
	
	@FXML private GridPane gridPane;
	
	private Map<String, Long> devActivities;
	private Map<String, Long> mainActivities;
	
	@FXML
	private void handleAddDevProcessButton() {
		showAddDevelopementProcessForm();
	}

	@FXML
	private void handleAddMainProcessButton() {
		showAddMaintananceProcessForm();
	}

	private void showAddDevelopementProcessForm() {
		try {
			Stage stage;
			Parent root;
			stage = (Stage) addDevProcessButton.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/project/AddDevelopementProcessForm.fxml"));
			root = (Parent) loader.load();
			AddProcessController addDevelopementProcessController = loader.<AddProcessController> getController();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			addDevelopementProcessController.initial();
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void showAddMaintananceProcessForm() {
		try {
			Stage stage;
			Parent root;
			stage = (Stage) addDevProcessButton.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/project/AddMaintananceProcessForm.fxml"));
			root = (Parent) loader.load();
			AddMaintananceProcessController addMaintananceProcessController = loader
					.<AddMaintananceProcessController> getController();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			addMaintananceProcessController.initial();
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private List<TreeItem<String>> createTreeItems(Set<String> keys) {
		List<TreeItem<String>> children = new ArrayList<TreeItem<String>>();
		for (String key : keys) {
			TreeItem<String> item = new TreeItem<String> (key);
			children.add(item);
		}
		
		return children;
	}
	
	private void showActivityInfo(String key) {
		Long activityId;
		Activity activity;
		String projectName;
		if(this.devActivities.containsKey(key)) {
			activityId = this.devActivities.get(key);
			activity = new Activity().getActivity(activityId);
			projectName = activity.getProjectNameOfActivity(activityId, "DevProcID");
		}
		else {
			activityId = this.mainActivities.get(key);
			activity = new Activity().getActivity(activityId);
			projectName = activity.getProjectNameOfActivity(activityId, "MainProcID");
		}
		
		this.gridPane.setVisible(true);
		this.activityNameLabel.setText(activity.getName());
		this.moduleNameLabel.setText(activity.getModule().getName());
		this.unitNameLabel.setText(activity.getUnit().getSpeciality());
		
		this.startDateLabel.setText(activity.getStartDate().toString());
		this.endDateLabel.setText(activity.getEndDate().toString());
		
		this.projectNameLabel.setText(projectName);
	}
	
	public void showAllActivities() {
		this.gridPane.setVisible(false);
		TreeItem<String> activityRoot = new TreeItem<String> ("فرآیندهای سازمان");
		activityRoot.setExpanded(true);

		Activity activity = new Activity();
		
		TreeItem<String> devActivityRoot = new TreeItem<String> ("فرآیندهای ایجاد");
		devActivityRoot.setExpanded(true);
		devActivities = activity.getDevActivities();
		devActivityRoot.getChildren().addAll(createTreeItems(devActivities.keySet()));
		
		TreeItem<String> mainActivityRoot = new TreeItem<String> ("فرآیندهای نگه داری");
		mainActivityRoot.setExpanded(true);
		mainActivities = activity.getMainActivities();
		mainActivityRoot.getChildren().addAll(createTreeItems(mainActivities.keySet()));
		
		activityRoot.getChildren().add(devActivityRoot);
		activityRoot.getChildren().add(mainActivityRoot);
		
		activityListTree.setRoot(activityRoot);
		activityListTree.getSelectionModel().selectedItemProperty().addListener( new ChangeListener() {

	        @Override
	        public void changed(ObservableValue observable, Object oldValue,
	                Object newValue) {

	            TreeItem<String> selectedItem = (TreeItem<String>) newValue;
	            String key = selectedItem.getValue();
	            showActivityInfo(key);
	        }
	      });
	}
}
