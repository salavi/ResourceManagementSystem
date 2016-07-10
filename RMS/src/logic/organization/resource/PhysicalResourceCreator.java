package logic.organization.resource;

import model.organization.resource.PhysicalResourceModel;
import model.organization.resource.ResourceModel;
import model.organization.unit.UnitAdapter;
import model.organization.unit.UnitModel;

public class PhysicalResourceCreator extends ResourceFactory {

	@Override
	public Resource createResource() {
		PhysicalResource physicalResource = new PhysicalResource();
		return physicalResource;
	}
	
	public ResourceModel createPhysicalResource(Long unitId, String resourceId, String type){
		PhysicalResourceModel physicalResourceModel = new PhysicalResourceModel();
		physicalResourceModel.setType(type);
		physicalResourceModel.setResourceId(resourceId);
		
		UnitModel unit = new UnitAdapter().getUnit(unitId);
		physicalResourceModel.setCurrentUnit(unit);
		
		resourceAdapter.addResource(physicalResourceModel);
		return physicalResourceModel;
	}


}
