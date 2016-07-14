package logic.organization.resource;

import java.util.List;

import logic.organization.unit.Unit;
import model.organization.resource.ResourceAdapter;
import model.organization.resource.ResourceModel;

public abstract class Resource {

	private Unit currentUnit;
	private String resourceId;

	public Resource() {

	}

	public Unit getCurrentUnit() {
		return currentUnit;
	}

	public void setCurrentUnit(Unit currentUnit) {
		this.currentUnit = currentUnit;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	
	public static List<String> getResourcesUnits(String type){
		List<String> units = ResourceAdapter.getInstance().findUnitsOfResources(type);
		return units;
	}
	
	public static List<String> getResourcesUnitCounts(String type){
		List<String> unitCounts = ResourceAdapter.getInstance().findUnitCountsOfResources(type);
		return unitCounts;
	}
	
	public static ResourceModel findResource(Long resourceId) {
		
		ResourceAdapter resourceAdapter = new ResourceAdapter();
		return resourceAdapter.getResource(resourceId);
	}
}
