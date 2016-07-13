package logic.organization.activity;

import java.time.LocalDate;
import java.util.Date;

import logic.organization.module.Module;
import logic.organization.unit.Unit;
import model.organization.activity.ActivityAdapter;
import model.organization.activity.ActivityModel;
import model.organization.module.ModuleAdapter;
import model.organization.module.ModuleModel;
import model.organization.unit.UnitAdapter;
import model.organization.unit.UnitModel;

public class Activity {
	private String name;
	private Date startDate;
	private Date endDate;
	private Module module;
	private Unit unit;
	
	public Activity(String name, Date startDate, Date endDate, Module module, Unit unit){
		this.setName(name);
		this.setStartDate(startDate);
		this.setEndDate(endDate);
		this.setModule(module);
		this.setUnit(unit);
	}
	
	public Activity(String name, Date startDate, Date endDate){
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
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
	
	public ActivityModel createActivityModel(Long moduleId, Long unitId) {
		ModuleModel module = ModuleAdapter.getInstance().getModule(moduleId);
		UnitModel unit = UnitAdapter.getInstance().getUnit(unitId);
		
		
				
		ActivityModel activityModel = new ActivityModel(this.name, startDate, endDate, module, unit);
		ActivityAdapter.getInstance().addActivity(activityModel);
		
		return activityModel;
	}
}
