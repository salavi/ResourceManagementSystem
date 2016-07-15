package controller.resourceManagement;



import java.io.IOException;
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
import logic.organization.resource.FinancialResourceCreator;
import logic.organization.resource.HumanResourceCreator;
import logic.organization.resource.InformationResourceCreator;
import logic.organization.resource.PhysicalResourceCreator;
import logic.organization.resource.Resource;
import logic.organization.resource.ResourceFactory;
import model.organization.resource.FinancialResourceModel;
import model.organization.resource.HumanResourceModel;
import model.organization.resource.InformationalResourceModel;
import model.organization.resource.PhysicalResourceModel;
import model.organization.resource.ResourceModel;

public class ResourceManagementController {
	@FXML private Button addResourceButton;
	@FXML private TreeView<String> resourceListTree; 
	
	@FXML private Label resourceTypeLabel;
	@FXML private Label resourceIdLabel;
	@FXML private Label currentUnitLabel;
	@FXML private Label firstDataLabel;
	@FXML private Label firstTitleLabel;
	@FXML private Label secondDataLabel;
	@FXML private Label secondTitleLabel;
	
	@FXML private GridPane gridPane;
	
	
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
	
	private List<TreeItem<String>> createTreeItems(Set<String> keys) {
		List<TreeItem<String>> children = new ArrayList<TreeItem<String>>();
		for (String key : keys) {
			TreeItem<String> item = new TreeItem<String> (key);
			children.add(item);
		}
		
		return children;
	}
	
	private void showResourceInfo(String key) {
		Long resourceId;
		ResourceModel resource = null;
		this.gridPane.setVisible(true);
		if (humanResources.containsKey(key)) {
        	resourceId = humanResources.get(key);
        	resource = Resource.createResourceModel(resourceId);
        	
        	this.resourceTypeLabel.setText(ResourceType.HUMANRESOUCE.getFarsiType());
        	
        	this.firstTitleLabel.setText("نام");
        	this.firstDataLabel.setText(((HumanResourceModel)resource).getFirstName());
        	
        	this.secondTitleLabel.setVisible(true);
        	this.secondTitleLabel.setText("نام خانوادگی");
        	this.secondDataLabel.setVisible(true);
        	this.secondDataLabel.setText(((HumanResourceModel)resource).getLastName());
        }
        else if (physicalResources.containsKey(key)) {
        	resourceId = physicalResources.get(key);
        	resource = Resource.createResourceModel(resourceId);
        	
        	this.resourceTypeLabel.setText(ResourceType.PHYSICALRESOURCE.getFarsiType());
        	
        	this.firstTitleLabel.setText("نوع");
        	this.firstDataLabel.setText(((PhysicalResourceModel)resource).getType());
        	
        	this.secondTitleLabel.setVisible(false);
        	this.secondDataLabel.setVisible(false);
        }
        else if (informationalResources.containsKey(key)) {
        	resourceId = informationalResources.get(key);
        	resource = Resource.createResourceModel(resourceId);
        	
        	this.resourceTypeLabel.setText(ResourceType.INFORMATIONALRESOURCE.getFarsiType());
        	
        	this.firstTitleLabel.setText("نوع");
        	this.firstDataLabel.setText(((InformationalResourceModel)resource).getType());
        	
        	this.secondTitleLabel.setVisible(false);
        	this.secondDataLabel.setVisible(false);
        }
        else if (financialResources.containsKey(key)) {
        	resourceId = financialResources.get(key);
        	resource = Resource.createResourceModel(resourceId);
        	
        	this.resourceTypeLabel.setText(ResourceType.FINANCIALRESOURCE.getFarsiType());
        	
        	this.firstTitleLabel.setText("بودجه");
        	this.firstDataLabel.setText(((FinancialResourceModel)resource).getAmount() + "");
        	
        	this.secondTitleLabel.setVisible(false);
        	this.secondDataLabel.setVisible(false);
        }
		
		this.resourceIdLabel.setText(resource.getResourceId());
		this.currentUnitLabel.setText(resource.getCurrentUnit().getSpecialty());
    }
		
	
	
	public void showAllResources() {
		this.gridPane.setVisible(false);
		TreeItem<String> resourceRoot = new TreeItem<String> ("منابع سازمان");
		resourceRoot.setExpanded(true);
		
		TreeItem<String> financialRootItem = new TreeItem<String> (ResourceType.FINANCIALRESOURCE.getFarsiType());
		financialRootItem.setExpanded(true);
		financialResources = new FinancialResourceCreator().getAllFinancialResources();
		financialRootItem.getChildren().addAll(createTreeItems(financialResources.keySet()));
		resourceRoot.getChildren().add(financialRootItem);


		
		TreeItem<String> humanRootItem = new TreeItem<String> (ResourceType.HUMANRESOUCE.getFarsiType());
		humanRootItem.setExpanded(true);
		humanResources = new HumanResourceCreator().getAllHumanResources();
		humanRootItem.getChildren().addAll(createTreeItems(humanResources.keySet()));
		resourceRoot.getChildren().add(humanRootItem);
		
		
		TreeItem<String> physicalRootItem = new TreeItem<String> (ResourceType.PHYSICALRESOURCE.getFarsiType());
		physicalRootItem.setExpanded(true);
		physicalResources = new PhysicalResourceCreator().getAllPhysicalResources();
		physicalRootItem.getChildren().addAll(createTreeItems(physicalResources.keySet()));
		resourceRoot.getChildren().add(physicalRootItem);
		
		TreeItem<String> informationalRootItem = new TreeItem<String> (ResourceType.INFORMATIONALRESOURCE.getFarsiType());
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
		            showResourceInfo(key);
		        }
		      });
	}

}
