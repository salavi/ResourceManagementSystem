package logic.organization.resource;

import java.util.ArrayList;
import java.util.Iterator;
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
	
	public static List<String> findExistingResourcesInUnits(String type){
		List<String> result = new ArrayList<>();
		ResourceAdapter resourceAdapter = ResourceAdapter.getInstance();
		Iterator<Object> it = resourceAdapter.findExistingResourcesInUnits(type).iterator();
		if(it == null)
			return null;
		while(it.hasNext()){
			Object[] tuples = (Object[]) it.next();
			result.add("Unit: " + tuples[0] + "  Specialty: " + tuples[1] + "    Count: " + tuples[2]);
		}
		return result;
	}
	
	public static ResourceModel createResourceModel(Long resourceId) {
		
		ResourceAdapter resourceAdapter = new ResourceAdapter();
		return resourceAdapter.getResource(resourceId);
	}
	
}
