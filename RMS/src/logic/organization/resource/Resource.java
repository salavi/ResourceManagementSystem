package logic.organization.resource;

import logic.organization.unit.Unit;

public abstract class Resource {

	private String resourceID;
	private Unit currentUnit;

	public String getResourceID() {
		return resourceID;
	}

	public void setResourceID(String resourceID) {
		this.resourceID = resourceID;
	}

	public Unit getCurrentUnit() {
		return currentUnit;
	}

	public void setCurrentUnit(Unit currentUnit) {
		this.currentUnit = currentUnit;
	}

}
