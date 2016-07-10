package logic.organization.resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.organization.resource.HumanResourceModel;
import model.organization.resource.ResourceAdapter;
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
	
	public Map<String, Long> getAllHumanResources() {
		List<HumanResourceModel> resources = new ResourceAdapter() .findAllHumanResources();
		Map<String, Long> convertedResources = new HashMap<>();
		for (HumanResourceModel humanResource : resources) {
			convertedResources.put(humanResource.getResourceId() + ": " + humanResource.getFirstName() + " " + humanResource.getLastName(), humanResource.getId());
		}
		
		return convertedResources;
	}

}
