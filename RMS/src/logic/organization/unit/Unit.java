package logic.organization.unit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.organization.resource.Resource;
import model.organization.resource.ResourceAdapter;
import model.organization.resource.ResourceModel;
import model.organization.unit.UnitAdapter;
import model.organization.unit.UnitModel;

public class Unit {
	// TODO
	private String unitId;
	private String specialty;
	private ArrayList<Resource> RequiredResources;
	private ArrayList<Resource> ExistingResources;
	
	public Unit(UnitModel unitModel) {
		this.unitId = unitModel.getUnitId();
		this.specialty = unitModel.getSpecialty();
	}

	public Unit() {
		// TODO Auto-generated constructor stub
	}

	public Map<String, Long> getAllUnits() {
		UnitAdapter unitAdapter = UnitAdapter.getInstance();
		List<UnitModel> units = unitAdapter.findAll();
		Map<String, Long> convertedUnits = new HashMap<>();
		for (UnitModel unit : units) {
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
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
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


	public UnitModel createUnitModel() {
		UnitModel unitModel = new UnitModel();
		unitModel.setSpecialty(this.specialty);
		unitModel.setUnitId(unitId);
		
		return unitModel;
	}
	

	public void addRequiredResourceToUnit(Long resourceId, Long unitId) {
		UnitAdapter unitAdapter = UnitAdapter.getInstance();
		UnitModel retrievedUnit = unitAdapter.getUnit(unitId);
		ResourceAdapter resourceAdapter = new ResourceAdapter();
		ResourceModel retrievedResource = resourceAdapter.getResource(resourceId);
		if (!retrievedResource.getCurrentUnit().getId().equals(retrievedUnit.getId())) {
			retrievedUnit.addRequiredResources(retrievedResource);
			unitAdapter.addUnit(retrievedUnit);
		}
	}


}
