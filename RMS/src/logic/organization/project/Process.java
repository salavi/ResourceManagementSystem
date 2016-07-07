package logic.organization.project;

import java.sql.Date;

import logic.organization.module.Module;
import logic.organization.unit.Unit;
import model.activity.ActivityAdapter;
import model.activity.ActivityModel;

public class Process {
	
	private String typeOfProcess;
//	private int procesTypeEnum;
	
//	private enum ProcessTypeEnum{
//		Development(1), Maintenance(2);
//		private int value;
//		private ProcessTypeEnum(int value){
//			this.value = value;
//		}
//	}
	
	public Process(String typeOfProcess){
		this.typeOfProcess = typeOfProcess;
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
