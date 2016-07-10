package logic.organization.resource;

import logic.organization.unit.Unit;

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
	
	
}
