package model.activity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import model.module.ModuleModel;
import model.unit.UnitModel;

@Entity
@Table(name = "Activity")
public class ActivityModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	@Column(name = "id")
	private Long id;

	private String name;
	private Date startDate;
	private Date endDate;
	private ModuleModel module;
	private UnitModel unit;
	
	public ActivityModel(String name, Date startDate, Date endDate, ModuleModel module, UnitModel unit) {
		this.setName(name);
		this.setStartDate(startDate);
		this.setEndDate(endDate);
		this.setModule(module);
		this.setUnit(unit);
	}

	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getStartDate() {
		return this.startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return this.endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public ModuleModel getModul() {
		return this.module;
	}
	
	public void setModule(ModuleModel module) {
		this.module = module;
	}
	
	public UnitModel getUnit() {
		return this.unit;
	}
	
	public void setUnit(UnitModel unit) {
		this.unit = unit;
	}
	
}
