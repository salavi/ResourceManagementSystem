package model.project;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import model.activity.ActivityModel;

@Entity
@Table(name = "Process")
public class ProcessModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	@Column(name = "id")
	private Long id;

	private String typeOfProcess;
	private Set<ActivityModel> activities;
	
	public String getTypeOfProcess() {
		return this.typeOfProcess;
	}
	
	public void setTypeOfProcess(String typeOfProcess) {
		this.typeOfProcess = typeOfProcess;
	}
	
	public Set<ActivityModel> getActivities() {
		return this.activities;
	}
	
	public void setActivities(Set<ActivityModel> activities) {
		this.activities = activities;
	}
}
