package controller.resourceManagement;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import logic.organization.resource.FinancialResourceCreator;
import logic.organization.resource.HumanResourceCreator;
import logic.organization.resource.InformationResourceCreator;
import logic.organization.resource.PhysicalResourceCreator;
import logic.organization.resource.ResourceFactory;

public class ResourceManagementController {
	@FXML private Button addResourceButton;
	@FXML private TreeView<String> resourceListTree; 
	
//	private final Node rootIcon = new ImageView(
//	        new Image(getClass().getResourceAsStream("folder_16.png"))
//	    );
	
	private Map<String, Long> humanResources;
	private Map<String, Long> informationalResources;
	private Map<String, Long> physicalResources;
	private Map<String, Long> financialResources;
	
	private ResourceFactory resourceFactory;
	
	
	@FXML
	private void handleAddResourceButton() {
		showAddResourceForm();
	}
	
	private void showAddResourceForm() {
		try {
			Stage stage;
			Parent root;
			stage = (Stage) addResourceButton.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/resourceManagement/AddResourceForm.fxml"));
			root = (Parent) loader.load();
			AddResourceController addResourceController = loader.<AddResourceController> getController();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			addResourceController.initial();
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
	
	private void showResourceInfo(Long resourceId, ResourceType resourceType) {
		
	}
	
	public void showAllResources() {
		TreeItem<String> resourceRoot = new TreeItem<String> ("منابع سازمان");
		
		TreeItem<String> financialRootItem = new TreeItem<String> (ResourceType.FINANCIALRESOURCE.getType());
		financialRootItem.setExpanded(true);
		financialResources = new FinancialResourceCreator().getAllFinancialResources();
		financialRootItem.getChildren().addAll(createTreeItems(financialResources.keySet()));
		resourceRoot.getChildren().add(financialRootItem);


		
		TreeItem<String> humanRootItem = new TreeItem<String> (ResourceType.HUMANRESOUCE.getType());
		humanRootItem.setExpanded(true);
		humanResources = new HumanResourceCreator().getAllHumanResources();
		humanRootItem.getChildren().addAll(createTreeItems(humanResources.keySet()));
		resourceRoot.getChildren().add(humanRootItem);
		
		
		TreeItem<String> physicalRootItem = new TreeItem<String> (ResourceType.PHYSICALRESOURCE.getType());
		physicalRootItem.setExpanded(true);
		physicalResources = new PhysicalResourceCreator().getAllPhysicalResources();
		physicalRootItem.getChildren().addAll(createTreeItems(physicalResources.keySet()));
		resourceRoot.getChildren().add(physicalRootItem);
		
		TreeItem<String> informationalRootItem = new TreeItem<String> (ResourceType.INFORMATIONALRESOURCE.getType());
		informationalRootItem.setExpanded(true);
		informationalResources = new InformationResourceCreator().getAllInformationalResources();
		informationalRootItem.getChildren().addAll(createTreeItems(informationalResources.keySet()));
		resourceRoot.getChildren().add(informationalRootItem);
		
		this.resourceListTree.setRoot(resourceRoot);
		 this.resourceListTree.getSelectionModel().selectedItemProperty().addListener( new ChangeListener() {

		        @Override
		        public void changed(ObservableValue observable, Object oldValue,
		                Object newValue) {

		            TreeItem<String> selectedItem = (TreeItem<String>) newValue;
		            String key = selectedItem.getValue();
		            if (humanResources.containsKey(key)) {
		            	Long resourceId = humanResources.get(key);
		            	showResourceInfo(resourceId, ResourceType.HUMANRESOUCE);
		            }
		            else if (physicalResources.containsKey(key)) {
		            	Long resourceId = physicalResources.get(key);
		            	showResourceInfo(resourceId, ResourceType.PHYSICALRESOURCE);
		            }
		            else if (informationalResources.containsKey(key)) {
		            	Long resourceId = informationalResources.get(key);
		            	showResourceInfo(resourceId, ResourceType.INFORMATIONALRESOURCE);
		            }
		            else if (financialResources.containsKey(key)) {
		            	Long resourceId = financialResources.get(key);
		            	showResourceInfo(resourceId, ResourceType.FINANCIALRESOURCE);
		            }
		        }

		      });
 
	}

}
