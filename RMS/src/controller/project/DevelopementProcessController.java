package controller.project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;
import logic.organization.activity.Activity;

public class DevelopementProcessController {
	@FXML
	Button addDevProcessButton;
	@FXML
	Button addMainProcessButton;
	@FXML
	TreeView<String> activityListTree;
	
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
	
	public void showAllActivities() {
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
	}
}
