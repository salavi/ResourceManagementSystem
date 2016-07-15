package logic.organization.project;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import controller.resourceManagement.ResourceType;
import logic.organization.module.Module;
import logic.organization.resourceUsage.ResourceUsageHistory;
import model.organization.activity.ActivityModel;
import model.organization.project.ProcessModel;
import model.organization.project.ProjectAdapter;
import model.organization.project.ProjectModel;
import model.organization.project.TechnologyModel;

public class Project {

	private String name;
	private int numOfInvolvedHumans;
	private int numOfModules;
	private Process developmentProcess;
	private Process maintenanceProcess;
	private ArrayList<Technology> listOfTechnologies;
	private ProjectModel projectModel;

	public Project() {
		developmentProcess = new Process("developmentProcess ");
		maintenanceProcess = new Process("maintenanceProcess");

	}

	public Project(String name, int numOfHumans, int numOfModules, Process developmentProcess,
			Process maintananceProcess, ArrayList<Technology> listOfTechnologies) {
		this.setName(name);
		this.setNumOfInvolvedHumans(numOfHumans);
		this.setNumOfModules(numOfModules);
		this.setDevelopmentProcess(developmentProcess);
		this.setMaintenanceProcess(maintananceProcess);
		this.setListOfTechnologies(listOfTechnologies);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumOfInvolvedHumans() {
		return numOfInvolvedHumans;
	}

	public void setNumOfInvolvedHumans(int numOfInvolvedHumans) {
		this.numOfInvolvedHumans = numOfInvolvedHumans;
	}

	public int getNumOfModules() {
		return numOfModules;
	}

	public void setNumOfModules(int numOfModules) {
		this.numOfModules = numOfModules;
	}

	public Process getDevelopmentProcess() {
		return developmentProcess;
	}

	public void setDevelopmentProcess(Process developmentProcess) {
		this.developmentProcess = developmentProcess;
	}

	public Process getMaintenanceProcess() {
		return maintenanceProcess;
	}

	public void setMaintenanceProcess(Process maintananceProcess) {
		this.maintenanceProcess = maintananceProcess;
	}

	public ArrayList<Technology> getListOfTechnologies() {
		return listOfTechnologies;
	}

	public void addTech(Technology technology) {
		this.getListOfTechnologies().add(technology);
	}

	public void removeTech(Technology technology) {
		this.getListOfTechnologies().remove(technology);
	}

	public void setListOfTechnologies(ArrayList<Technology> listOfTechnologies) {
		this.listOfTechnologies = listOfTechnologies;
	}

	public void addProjectToDB() {
		ArrayList<TechnologyModel> listOfTechnologyModel = new ArrayList<>();
		for (Technology technology : listOfTechnologies) {
			listOfTechnologyModel.add(new TechnologyModel(technology.getName(), technology.getGoalOfUsage()));
		}
		ProcessModel developmentProcessModel = new ProcessModel(this.getDevelopmentProcess().getTypeOfProcess());
		ProcessModel maintananceProcessModel = new ProcessModel(this.getMaintenanceProcess().getTypeOfProcess());
		this.projectModel = new ProjectModel(this.getName(), this.getNumOfInvolvedHumans(), this.getNumOfModules(),
				developmentProcessModel, maintananceProcessModel, listOfTechnologyModel);

		ProjectAdapter projectAdpt = ProjectAdapter.getInstance();
		projectAdpt.addProject(this.projectModel);
	}

	public Map<String, Long> getAllProjects() {
		ProjectAdapter projectAdapter = ProjectAdapter.getInstance();
		List<ProjectModel> projectModels = projectAdapter.findAll();
		Map<String, Long> map = new HashMap<>();
		int counter = 1;
		for (ProjectModel projectModel : projectModels) {
			map.put(counter + ". " + projectModel.getName(), projectModel.getId());
			++counter;
		}
		return map;
	}

	public void addDevelopementProcess(Long projectId, Long unitId, String activiy, String moduleName, String moduleId,
			LocalDate localStartDate, LocalDate localEndDate, Set<Long> resources) {
		ProjectAdapter projectAdapter = ProjectAdapter.getInstance();
		projectModel = projectAdapter.getProject(projectId);

		Module module = new Module();
		module.setModuleId(moduleId);
		module.setName(moduleName);

		Date startDate = Date.from(localStartDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date endDate = Date.from(localEndDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

		developmentProcess.setProcessModel(projectModel.getDevelopementProcess());
		ActivityModel activityModel = developmentProcess.addActivity(activiy, module, unitId, startDate, endDate);

		projectAdapter.addProject(projectModel);
		new ResourceUsageHistory().createRUH(activityModel, activityModel.getUnit(), projectModel, resources);

	}

	public void addMaintananceProcess(Long projectId, Long moduleId, Long unitId, String activity,
			LocalDate localStartDate, LocalDate localEndDate, Set<Long> resources) {
		Date startDate = Date.from(localStartDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date endDate = Date.from(localEndDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

		ProjectAdapter projectAdapter = ProjectAdapter.getInstance();
		projectModel = projectAdapter.getProject(projectId);

		maintenanceProcess.setProcessModel(projectModel.getMaintananceProcess());
		ActivityModel activityModel = maintenanceProcess.addActivity(activity, moduleId, unitId, startDate, endDate);

		projectAdapter.addProject(projectModel);
		new ResourceUsageHistory().createRUH(activityModel, activityModel.getUnit(), projectModel, resources);
	}

	public List<Long> findSimilarProjects(int minNumOfHumans, int maxNumOfHumans, int minNumOfModules,
			int maxNumOfModules, String[] technologies) {

		ProjectAdapter projectAdapter = ProjectAdapter.getInstance();
		List<Long> projectIds = projectAdapter.findSimilarProjects(minNumOfHumans, maxNumOfHumans, minNumOfModules,
				maxNumOfModules, technologies);

//		ResourceUsageHistoryAdapter resourceUsageHistoryAdapter = ResourceUsageHistoryAdapter.getInstance();
		
		return projectIds;
	}
	
	
	public Map<String, List<String>> getAResourceTypeUsedInProjects(String type){
		Map<String, List<String>> projectNameResourcesList = new HashMap<>();
		ProjectAdapter projectAdapter = ProjectAdapter.getInstance();
		
		Iterator<Object> iterator = null;
		switch (type){
		case "Financial":
			iterator = projectAdapter.getFinancialResourcesUsedInProjects().iterator();
			break;
		case "Information":
			iterator = projectAdapter.getInformationalResourcesUsedInProjects().iterator();
			break;
		case "Human":
			iterator = projectAdapter.getHumanResourcesUsedInProjects().iterator();
			break;
		case "Physical":
			iterator = projectAdapter.getPhysicalResourcesUsedInProjects().iterator();
			break;
		}
		
		if(iterator == null)
			return null;
		
		while ( iterator.hasNext() ) {
		    Object[] tuple = (Object[]) iterator.next();
		    String projectName = (String) tuple[0];
		    String resource;
		    if(tuple[1] == null)
		    	continue;
		    else if(type == ResourceType.HUMANRESOUCE.getEnglishType())
		    	resource = tuple[1] + " " + tuple[2];
		    else
		    	resource = tuple[1] + "";
		    if(!projectNameResourcesList.containsKey(projectName)){
				projectNameResourcesList.put(projectName, new ArrayList<>());
			}
			projectNameResourcesList.get(projectName).add(resource);
		}				
		return projectNameResourcesList;
	}
	
	public List<String> getAllProjectNamesUsedInResourceUsageHistory(){
		ProjectAdapter projectAdapter = ProjectAdapter.getInstance();
		return projectAdapter.getAllProjectNamesUsedInResourceUsageHistory();
	}
}

