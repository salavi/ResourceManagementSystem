package controller.resourceManagement;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import logic.organization.resource.FinancialResourceCreator;
import logic.organization.resource.HumanResourceCreator;
import logic.organization.resource.InformationResourceCreator;
import logic.organization.resource.PhysicalResourceCreator;
import logic.organization.resource.Resource;
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
			addResourceController.setReturnState("/view/RootLayout.fxml");
			Scene scene = new Scene(root);
			stage.setScene(scene);
			addResourceController.initial();
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	}
	
	private List<TreeItem<String>> createTreeItems(Set<String> keys) {
		List<TreeItem<String>> children = new ArrayList();
		for (String key : keys) {
			TreeItem<String> item = new TreeItem<String> (key);
			children.add(item);
		}
		
		return children;
	}
}
