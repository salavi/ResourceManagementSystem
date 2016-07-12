package controller.prediction;

import javafx.fxml.FXML;

public class PredictionController {

	@FXML
	private ResourcePredictionController resourcePredictionController;
	@FXML
	private RequirementPredictionController requirementPredictionController;

	public ResourcePredictionController getResourcePredictionController() {
		return resourcePredictionController;
	}

	public void setResourcePredictionController(ResourcePredictionController resourcePredictionController) {
		this.resourcePredictionController = resourcePredictionController;
	}

	public RequirementPredictionController getRequirementPredictionController() {
		return requirementPredictionController;
	}

	public void setRequirementPredictionController(RequirementPredictionController requirementPredictionController) {
		this.requirementPredictionController = requirementPredictionController;
	}

}
