package logic.organization.resourceUsage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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


	public Map<String, List<String>> getResourceUsageHistoryInfo(){
		Map<String, List<String>> result = new HashMap<>();
		ResourceUsageHistoryAdapter resourceUsageHistoryAdapter = ResourceUsageHistoryAdapter.getInstance();
		Iterator<Object> it = resourceUsageHistoryAdapter.getResourceUsageHistoryInfo().iterator();
		if(it == null)
			return null;
		while(it.hasNext()){
			Object[] tuples = (Object[]) it.next();			
			if(!result.containsKey(tuples[0] + "")){
				result.put(tuples[0] + "", new ArrayList<>());
		    }
			String aHistory = "StartDate: " + tuples[1].toString() + "   EndDate: " + tuples[2].toString() + "   Type of Process: " + tuples[3].toString() + "   Project Name: " + tuples[4];
		    result.get(tuples[0] + "").add(aHistory);
		}
		return result;
	}
	
	public Map<String, String> getDistinctResourcesUsedInRUHResources(String type){
		Map<String, String> result = new HashMap<>();
		ResourceUsageHistoryAdapter resourceUsageHistoryAdapter = ResourceUsageHistoryAdapter.getInstance();
		Iterator<Object> it = resourceUsageHistoryAdapter.getDistinctResourcesUsedInRUHResources(type).iterator();
		if(it == null)
			return null;
		while(it.hasNext()){
			Object[] tuples = (Object[]) it.next();
			if(!result.containsKey(tuples[0])){
				if(type.equals("Human"))
					result.put(tuples[0] + "", tuples[1] + "  " + tuples[2]);
				else
					result.put(tuples[0] + "", tuples[1] + "");
			}
		}
		return result;
	}
}
