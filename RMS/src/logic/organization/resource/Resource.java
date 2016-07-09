package logic.organization.resource;

import logic.organization.unit.Unit;

public abstract class Resource {

	private Unit currentUnit;
	private String ResourceId;

	public Unit getCurrentUnit() {
		return currentUnit;
	}

	public void setCurrentUnit(Unit currentUnit) {
		this.currentUnit = currentUnit;
	}

	public String getResourceId() {
		return ResourceId;
	}

	public void setResourceId(String resourceId) {
		ResourceId = resourceId;
	}
	
	
}
