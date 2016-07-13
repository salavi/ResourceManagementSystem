package logic.organization.resourceUsage;

import java.util.HashSet;
import java.util.Set;

import logic.organization.activity.Activity;
import logic.organization.project.Project;
import logic.organization.resource.Resource;
import logic.organization.unit.Unit;
import model.organization.activity.ActivityAdapter;
import model.organization.activity.ActivityModel;
import model.organization.project.ProjectAdapter;
import model.organization.project.ProjectModel;
import model.organization.resource.ResourceAdapter;
import model.organization.resource.ResourceModel;
import model.organization.resourceUsage.ResourceUsageHistoryAdapter;
import model.organization.resourceUsage.ResourceUsageHistoryModel;
import model.organization.unit.UnitModel;

public class ResourceUsageHistory {
	private Unit unit;
	private Project project;
	private Set<Resource> resources;
	private Activity activity;
	
	
	public ResourceUsageHistoryModel createRUH(ActivityModel activityModel, UnitModel unitModel, ProjectModel projectModel, Set<Long> resources) {
		Set<ResourceModel> resourceModels = new HashSet<>();
		ResourceAdapter resourceAdapter = ResourceAdapter.getInstance();
		for (Long resourceId : resources) {
			resourceModels.add(resourceAdapter.getResource(resourceId));
		}
		
		ResourceUsageHistoryModel ruhModel = new ResourceUsageHistoryModel();
		ruhModel.setActivity(activityModel);
		ruhModel.setProject(projectModel);
		ruhModel.setResources(resourceModels);
		ruhModel.setUnit(unitModel);
		
		ResourceUsageHistoryAdapter.getInstance().addResourceUsageHistory(ruhModel);
		return ruhModel;
	}


	public Unit getUnit() {
		return unit;
	}


	public void setUnit(Unit unit) {
		this.unit = unit;
	}


	public Project getProject() {
		return project;
	}


	public void setProject(Project project) {
		this.project = project;
	}


	public Set<Resource> getResources() {
		return resources;
	}


	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}


	public Activity getActivity() {
		return activity;
	}


	public void setActivity(Activity activity) {
		this.activity = activity;
	}


	public ResourceUsageHistoryModel createRUH(Long activityId, Long projectId, Long resourceId) {
		ProjectModel projectModel = ProjectAdapter.getInstance().getProject(projectId);
		ActivityModel activityModel = ActivityAdapter.getInstance().getActivity(activityId);
		ResourceModel resourceModel = ResourceAdapter.getInstance().getResource(resourceId);
		
		Set<ResourceModel> resources = new HashSet<>();
		resources.add(resourceModel);
		
		ResourceUsageHistoryModel ruhModel = new ResourceUsageHistoryModel();
		ruhModel.setActivity(activityModel);
		ruhModel.setProject(projectModel);
		ruhModel.setUnit(activityModel.getUnit());
		ruhModel.setResources(resources);
		
		ResourceUsageHistoryAdapter.getInstance().addResourceUsageHistory(ruhModel);
		return ruhModel;
	}
}
