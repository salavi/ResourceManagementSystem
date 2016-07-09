package logic.organization.activity;

import java.sql.Date;

import logic.organization.module.Module;
import logic.organization.unit.Unit;

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
	
}
