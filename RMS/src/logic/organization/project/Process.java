package logic.organization.project;


import java.time.LocalDate;
import java.util.ArrayList;

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
	
	public void addActivity(String name, Module module, Long unitId, LocalDate startDate, LocalDate endDate){
		//TODO
		Activity activity = new Activity(name, startDate, endDate);
		activity.setModule(module);
		this.activities.add(activity);
		
		activity.createActivityModel(unitId);
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
