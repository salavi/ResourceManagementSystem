package logic.organization.resource;

import model.organization.resource.InformationalResourceModel;
import model.organization.resource.ResourceModel;
import model.organization.unit.UnitAdapter;
import model.organization.unit.UnitModel;

public class InformationResourceCreator extends ResourceFactory {

	@Override
	public Resource createResource() {
		InformationalResource informationResource = new InformationalResource();
		return informationResource;
	}
	
	public ResourceModel createInformationalResource(Long unitId, String resourceId, String type){
		InformationalResourceModel informationalResourceModel = new InformationalResourceModel();
		informationalResourceModel.setType(type);
		informationalResourceModel.setResourceId(resourceId);
		
		UnitModel unit = new UnitAdapter().getUnit(unitId);
		informationalResourceModel.setCurrentUnit(unit);
		
		resourceAdapter.addResource(informationalResourceModel);
		return informationalResourceModel;
	}
}
