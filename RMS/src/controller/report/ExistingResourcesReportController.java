package controller.report;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.TreeView;
import logic.organization.resource.ResourceFactory;

public class ExistingResourcesReportController {
	@FXML private TreeView<String> existingResourcesListTree; 
	
	private List<String> humanResources;
	private List<String> informationalResources;
	private List<String> physicalResources;
	private List<String> financialResources;
	
	private ResourceFactory resourceFactory;
	
	
}
