package logic.organization.project;


import java.util.ArrayList;
import java.util.Date;

import logic.organization.activity.Activity;
import logic.organization.module.Module;
import model.organization.activity.ActivityModel;
import model.organization.project.ProcessModel;


public class Process {
	
	private String typeOfProcess;
	private ArrayList<Activity> activities;
	
	private ProcessModel processModel;
	
	
	public Process(String typeOfProcess){
		this.typeOfProcess = typeOfProcess;
		activities = new ArrayList<>();
	}
	
	public void addActivity(String name, Module module, Long unitId, Date startDate, Date endDate){
		//TODO
		Activity activity = new Activity(name, startDate, endDate);
		activity.setModule(module);
		this.activities.add(activity);
		
		ActivityModel activityModel = activity.createActivityModel(unitId);
		this.processModel.addAcitity(activityModel);
	}
	
	public void addActivity(String name, Long moduleId, Long unitId, Date startDate, Date endDate){
		//TODO
		Activity activity = new Activity(name, startDate, endDate);
		this.activities.add(activity);
		
		ActivityModel activityModel = activity.createActivityModel(moduleId, unitId);
		this.processModel.addAcitity(activityModel);
	}

	public String getTypeOfProcess() {
		return typeOfProcess;
	}

	public void setTypeOfProcess(String typeOfProcess) {
		this.typeOfProcess = typeOfProcess;
	}

	public ProcessModel getProcessModel() {
		return processModel;
	}

	public void setProcessModel(ProcessModel processModel) {
		this.processModel = processModel;
	}

}
