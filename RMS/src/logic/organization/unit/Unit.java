package logic.organization.unit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import logic.organization.resource.Resource;
import model.organization.resource.ResourceAdapter;
import model.organization.resource.ResourceModel;
import model.organization.unit.UnitAdapter;
import model.organization.unit.UnitModel;

public class Unit {
	//TODO
	private String unitId;
	private String speciality;
	private ArrayList<Resource> RequiredResources;
	private ArrayList<Resource> ExistingResources;
	
	
	public Map<String, Long> getAllUnits() {
		UnitAdapter unitAdapter = new UnitAdapter();
		List<UnitModel> units = unitAdapter.findAll();
		Map<String, Long> convertedUnits = new HashMap<>();
		for (UnitModel unit: units) {
			convertedUnits.put(unit.getUnitId() + ": " + unit.getSpecialty(), unit.getId());
		}
		
		return convertedUnits;
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
		return ExistingResources;
	}
	public void setExistingResource(ArrayList<Resource> existingResources) {
		ExistingResources = existingResources;
	}

	public void addRequiredResourceToUnit(Long resourceId, Long unitId) {
		UnitAdapter unitAdapter = new UnitAdapter();
		UnitModel retrievedUnit = unitAdapter.getUnit(unitId);
		ResourceAdapter resourceAdapter = new ResourceAdapter();
		ResourceModel retrievedResource = resourceAdapter.getResource(resourceId);
		retrievedUnit.addRequiredResources(retrievedResource);
		unitAdapter.addUnit(retrievedUnit);
	}
	
}
