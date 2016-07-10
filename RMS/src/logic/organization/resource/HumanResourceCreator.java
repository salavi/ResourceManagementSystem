package logic.organization.resource;

import model.organization.resource.FinancialResourceModel;
import model.organization.resource.HumanResourceModel;
import model.organization.resource.ResourceModel;
import model.organization.unit.UnitAdapter;
import model.organization.unit.UnitModel;

public class HumanResourceCreator extends ResourceFactory {

	@Override
	public Resource createResource() {
		HumanResource humanResource = new HumanResource();
		return humanResource;
	}
	public ResourceModel createHumanResource(Long unitId, String resourceId, String firstName, String lastName){
		HumanResourceModel humanResourceModel = new HumanResourceModel();
		humanResourceModel.setFirstName(firstName);
		humanResourceModel.setLastName(lastName);
		humanResourceModel.setResourceId(resourceId);
		
		UnitModel unit = new UnitAdapter().getUnit(unitId);
		humanResourceModel.setCurrentUnit(unit);
		
		resourceAdapter.addResource(humanResourceModel);
		return humanResourceModel;
	}

}
