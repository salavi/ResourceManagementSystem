package logic.organization.unit;

import java.util.ArrayList;
import java.util.List;

import logic.organization.resource.Resource;
import model.organization.unit.UnitAdapter;
import model.organization.unit.UnitModel;

public class Unit {
	//TODO
	private String unitId;
	private String speciality;
	private ArrayList<Resource> RequiredResources;
	private ArrayList<Resource> ExistingResources;
	
	
	public ArrayList<String> getAllUnitIds() {
		UnitAdapter unitAdapter = new UnitAdapter();
		List<UnitModel> units = unitAdapter.findAll();
		ArrayList<String> unitIds = new ArrayList<>();
		for (UnitModel unit: units) {
			unitIds.add(unit.getUnitId());
		}
		
		return unitIds;
	}
	
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
