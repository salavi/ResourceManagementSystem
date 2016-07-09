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
	
}
