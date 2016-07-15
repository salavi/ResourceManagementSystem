package logic.organization.resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.organization.resource.PhysicalResourceModel;
import model.organization.resource.ResourceAdapter;
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

	public Map<String, Long> getAllPhysicalResources() {
		List<PhysicalResourceModel> resources = new ResourceAdapter() .findAllPhysicalResources();
		Map<String, Long> convertedResources = new HashMap<>();
		int i = 1;
		for (PhysicalResourceModel physicalResource : resources) {
			convertedResources.put(physicalResource.getResourceId() + ": " + physicalResource.getType(), physicalResource.getId());
		}
		
		return convertedResources;
	}


}
