package logic.organization.activity;

import java.time.LocalDate;

import logic.organization.module.Module;
import logic.organization.unit.Unit;
import model.organization.activity.ActivityAdapter;
import model.organization.activity.ActivityModel;
import model.organization.module.ModuleModel;
import model.organization.unit.UnitAdapter;
import model.organization.unit.UnitModel;

public class Activity {
	private String name;
	private LocalDate startDate;
	private LocalDate endDate;
	private Module module;
	private Unit unit;
	
	public Activity(String name, LocalDate startDate, LocalDate endDate, Module module, Unit unit){
		this.setName(name);
		this.setStartDate(startDate);
		this.setEndDate(endDate);
		this.setModule(module);
		this.setUnit(unit);
	}
	
	public Activity(String name, LocalDate startDate, LocalDate endDate){
		this.setName(name);
		this.setStartDate(startDate);
		this.setEndDate(endDate);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	
	public ActivityModel createActivityModel() {
		ModuleModel module = this.module.createModuleModel();
		UnitModel unit = this.unit.createUnitModel();
		
		ActivityModel activityModel = new ActivityModel(this.name, startDate, endDate, module, unit);
		ActivityAdapter.getInstance().addActivity(activityModel);
		
		return activityModel;
	}

	public ActivityModel createActivityModel(Long unitId) {
		ModuleModel module = this.module.createModuleModel(); 
		UnitModel unit = UnitAdapter.getInstance().getUnit(unitId);
				
		ActivityModel activityModel = new ActivityModel(this.name, startDate, endDate, module, unit);
		ActivityAdapter.getInstance().addActivity(activityModel);
		
		return activityModel;
	}
}
