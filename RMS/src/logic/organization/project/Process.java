package logic.organization.project;

import java.sql.Date;

import logic.organization.module.Module;
import logic.organization.unit.Unit;


public class Process {
	
	private String typeOfProcess;
	
	public Process(String typeOfProcess){
		this.setTypeOfProcess(typeOfProcess);
	}
	
	public void addActivity(String name, Module module, Unit unit, Date startDate, Date endDate){
		//TODO
//		ActivityModel activityModel = new ActivityModel(name, startDate, endDate, module, unit);
//		ActivityAdapter activityAdapter = ActivityAdapter.getInstance();
//		activityAdapter.addActivity(activityModel);
	}

	public String getTypeOfProcess() {
		return typeOfProcess;
	}

	public void setTypeOfProcess(String typeOfProcess) {
		this.typeOfProcess = typeOfProcess;
	}
	
}
