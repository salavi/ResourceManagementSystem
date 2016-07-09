package model.organization.project;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import model.organization.activity.ActivityModel;

@Entity
@Table(name = "Process")
public class ProcessModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	@Column(name = "id")
	private Long id;

	private String typeOfProcess;
	private List<ActivityModel> activities;

	public ProcessModel(String typeOfProcess) {
		this.setTypeOfProcess(typeOfProcess);
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTypeOfProcess() {
		return this.typeOfProcess;
	}

	public void setTypeOfProcess(String typeOfProcess) {
		this.typeOfProcess = typeOfProcess;
	}

	public List<ActivityModel> getActivities() {
		return this.activities;
	}

	public void setActivities(List<ActivityModel> activities) {
		this.activities = activities;
	}
}
