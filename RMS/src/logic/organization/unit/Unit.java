package logic.organization.unit;

import java.util.ArrayList;

import logic.organization.resource.Resource;

public class Unit {
	//TODO
	private String unitId;
	private String speciality;
	private ArrayList<Resource> RequiredResources;
	private ArrayList<Resource> ExistingResource;
	
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public ArrayList<Resource> getRequiredResources() {
		return RequiredResources;
	}
	public void setRequiredResources(ArrayList<Resource> requiredResources) {
		RequiredResources = requiredResources;
	}
	public ArrayList<Resource> getExistingResource() {
		return ExistingResource;
	}
	public void setExistingResource(ArrayList<Resource> existingResource) {
		ExistingResource = existingResource;
	}
	
}
