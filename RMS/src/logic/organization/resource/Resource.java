package logic.organization.resource;

import logic.organization.unit.Unit;

public abstract class Resource {

	private Unit currentUnit;

	public Unit getCurrentUnit() {
		return currentUnit;
	}

	public void setCurrentUnit(Unit currentUnit) {
		this.currentUnit = currentUnit;
	}

}
