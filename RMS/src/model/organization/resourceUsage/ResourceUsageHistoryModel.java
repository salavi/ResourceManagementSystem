package model.organization.resourceUsage;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import model.organization.activity.ActivityModel;
import model.organization.project.ProjectModel;
import model.organization.resource.ResourceModel;
import model.organization.unit.UnitModel;

@Entity  
@Table(name="ResourceUsageHistory")  
@PrimaryKeyJoinColumn(name="ID")
public class ResourceUsageHistoryModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	@Column(name = "id")
	private Long id;
	
	private ProjectModel project;
	private ActivityModel activity;
	private UnitModel unit;
	
	private Set<ResourceModel> resources;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public ProjectModel getProject() {
		return project;
	}

	public void setProject(ProjectModel project) {
		this.project = project;
	}

	public ActivityModel getActivity() {
		return activity;
	}

	public void setActivity(ActivityModel activity) {
		this.activity = activity;
	}

	public UnitModel getUnit() {
		return unit;
	}

	public void setUnit(UnitModel unit) {
		this.unit = unit;
	}

	public Set<ResourceModel> getResources() {
		return resources;
	}

	public void setResources(Set<ResourceModel> resources) {
		this.resources = resources;
	}
	
}
