package logic.organization.activity;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
	
	public Activity() {
		
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
	
	public Map<String, Long> getDevActivities() {
		List<Object> activities = ActivityAdapter.getInstance().findAll("DevelopmentProcess");
		Map<String, Long> convertedActivities = new HashMap<String, Long>();
		Iterator it = activities.iterator();
		while (it.hasNext()) {
			Object[] triple = (Object[]) it.next();
			for (Object object : triple) {
				System.out.println(object);
			}
			convertedActivities.put(triple[0] +  ": " + triple[1], ((BigInteger) triple[0]).longValue());

		}
		return convertedActivities;
	}
	
	public Map<String, Long> getMainActivities() {
		List<Object> activities = ActivityAdapter.getInstance().findAll("MaintananceProcess");
		Map<String, Long> convertedActivities = new HashMap<String, Long>();
		Iterator it = activities.iterator();
		while (it.hasNext()) {
			Object[] triple = (Object[]) it.next();
			convertedActivities.put(triple[0] +  ": " + triple[1], ((BigInteger) triple[0]).longValue());
		}
		return convertedActivities;
	}

	public Map<String, Long> getActivitiesOfProject(Long projectId) {
		List<ActivityModel> activities = ActivityAdapter.getInstance().findActivitiesOfProject(projectId);
		Map<String, Long> convertedActivities = new HashMap<String, Long>();
		int i = 1;
		for (ActivityModel activityModel : activities) {
			convertedActivities.put(i + ". " + activityModel.getName(), activityModel.getId());
			i++;
		}
		return convertedActivities;
	}

	public Activity getActivity(Long activityId) {
		ActivityModel activityModel = ActivityAdapter.getInstance().getActivity(activityId);
		this.setStartDate(activityModel.getStartDate());
		this.setEndDate(activityModel.getEndDate());
		this.setModule(new Module(activityModel.getModule()));
		this.setUnit(new Unit(activityModel.getUnit()));
		
		return this;
	}

	public String getProjectNameOfActivity(Long activityId, String processType) {
		return ActivityAdapter.getInstance().getProjectName(activityId, processType);
	}
	
}
