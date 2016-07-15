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
		listOfTechnologies = new ArrayList<>();

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

	public Map<String, List<String>> findSimilarProjectsHumanResources(int minNumOfHumans, int maxNumOfHumans,
			int minNumOfModules, int maxNumOfModules, String[] technologies) {
		Map<String, List<String>> resultMap = new HashMap();
		ProjectAdapter projectAdapter = ProjectAdapter.getInstance();
		Iterator<Object> dbIterator = projectAdapter.findSimilarProjectsHumanResources(minNumOfHumans, maxNumOfHumans,
				minNumOfModules, maxNumOfModules, technologies).iterator();

		while (dbIterator.hasNext()) {
			Object[] tuple = (Object[]) dbIterator.next();
			String projectName = (String) tuple[0];
			String humanResourceFirstName, humanResourceLastName;
			if (tuple[1] != null) {
				humanResourceFirstName = (String) tuple[1];
				humanResourceLastName = (String) tuple[2];
				if (!resultMap.containsKey(projectName)) {
					resultMap.put(projectName, new ArrayList<>());
				}
				resultMap.get(projectName).add(humanResourceFirstName + " " + humanResourceLastName);
			}
		}
		return resultMap;
	}

	public Map<String, List<String>> findSimilarProjectsInformationalResources(int minNumOfHumans, int maxNumOfHumans,
			int minNumOfModules, int maxNumOfModules, String[] technologies) {
		Map<String, List<String>> resultMap = new HashMap();
		ProjectAdapter projectAdapter = ProjectAdapter.getInstance();
		Iterator<Object> dbIterator = projectAdapter.findSimilarProjectsInformationalResources(minNumOfHumans, maxNumOfHumans,
				minNumOfModules, maxNumOfModules, technologies).iterator();

		while (dbIterator.hasNext()) {
			Object[] tuple = (Object[]) dbIterator.next();
			String projectName = (String) tuple[0];
			String informationalResourceType;
			if (tuple[1] != null) {
				informationalResourceType = (String) tuple[1];
				if (!resultMap.containsKey(projectName)) {
					resultMap.put(projectName, new ArrayList<>());
				}
				resultMap.get(projectName).add(informationalResourceType);
			}
		}
		return resultMap;
	}

	public Map<String, List<String>> findSimilarProjectsPhysicalResources(int minNumOfHumans, int maxNumOfHumans,
			int minNumOfModules, int maxNumOfModules, String[] technologies) {
		Map<String, List<String>> resultMap = new HashMap();
		ProjectAdapter projectAdapter = ProjectAdapter.getInstance();
		Iterator<Object> dbIterator = projectAdapter.findSimilarProjectsPhysicalResources(minNumOfHumans,
				maxNumOfHumans, minNumOfModules, maxNumOfModules, technologies).iterator();

		while (dbIterator.hasNext()) {
			Object[] tuple = (Object[]) dbIterator.next();
			String projectName = (String) tuple[0];
			String physicalResourceType;
			if (tuple[1] != null) {
				physicalResourceType = (String) tuple[1];
				if (!resultMap.containsKey(projectName)) {
					resultMap.put(projectName, new ArrayList<>());
				}
				resultMap.get(projectName).add(physicalResourceType);
			}
		}
		return resultMap;

	}
	
	public Map<String, List<String>> findSimilarProjectsFinancialResources(int minNumOfHumans, int maxNumOfHumans,
			int minNumOfModules, int maxNumOfModules, String[] technologies) {
		Map<String, List<String>> resultMap = new HashMap();
		ProjectAdapter projectAdapter = ProjectAdapter.getInstance();
		Iterator<Object> dbIterator = projectAdapter.findSimilarProjectsFinancialResources(minNumOfHumans,
				maxNumOfHumans, minNumOfModules, maxNumOfModules, technologies).iterator();

		while (dbIterator.hasNext()) {
			Object[] tuple = (Object[]) dbIterator.next();
			String projectName = (String) tuple[0];
			String financialResourceAmount;
			if (tuple[1] != null) {
				financialResourceAmount = Double.toString((Double)tuple[1]);
				if (!resultMap.containsKey(projectName)) {
					resultMap.put(projectName, new ArrayList<>());
				}
				resultMap.get(projectName).add(financialResourceAmount);
			}
		}
		return resultMap;

	}

	public Map<String, List<String>> getFinancialResourcesUsedInProjects() {
		Map<String, List<String>> myMap = new HashMap();
		ProjectAdapter projectAdapter = new ProjectAdapter();
		Iterator<Object> iterator = projectAdapter.getFinancialResourcesUsedInProjects().iterator();

		List<ProjectNameFinancialAmount> projNameFinancialAmount = new ArrayList<>();
		while (iterator.hasNext()) {
			Object[] tuple = (Object[]) iterator.next();
			ProjectNameFinancialAmount row = new ProjectNameFinancialAmount();
			row.projectName = (String) tuple[0];
			if (tuple[1] == null)
				row.financialAmount = 0;
			else
				row.financialAmount = (double) tuple[1];
			projNameFinancialAmount.add(row);
			System.out.println(row.toString());
		}

		for (ProjectNameFinancialAmount pnfa : projNameFinancialAmount) {
			if (!myMap.containsKey(pnfa.projectName)) {
				myMap.put(pnfa.projectName, new ArrayList<>());
			}
			myMap.get(pnfa.projectName).add("" + (pnfa.financialAmount));
		}

		return myMap;
	}

	public Map<String, List<String>> getInformationalResourcesUsedInProjects() {
		Map<String, List<String>> myMap = new HashMap();
		ProjectAdapter projectAdapter = new ProjectAdapter();
		Iterator<Object> iterator = projectAdapter.getInformationalResourcesUsedInProjects().iterator();

		while (iterator.hasNext()) {
			Object[] tuple = (Object[]) iterator.next();
			String projectName = (String) tuple[0];
			String informationalResourceType;
			if (tuple[1] == null)
				informationalResourceType = "--";
			else
				informationalResourceType = (String) tuple[1];
			if (!myMap.containsKey(projectName)) {
				myMap.put(projectName, new ArrayList<>());
			}
			myMap.get(projectName).add(informationalResourceType);
		}
		return myMap;
	}

	public Project getProject(Long projectId) {
		ProjectModel projectModel = ProjectAdapter.getInstance().getProject(projectId);
		this.setName(projectModel.getName());
		this.setNumOfInvolvedHumans(projectModel.getNumOfInvolvedHumans());
		this.setNumOfModules(projectModel.getNumOfModules());
		
		for (TechnologyModel technology : projectModel.getListOfTechnologies()) {
			this.listOfTechnologies.add(new Technology(technology.getName(), technology.getGoalOfUsage()));
		}
		
		return this;
	}
}

class ProjectNameFinancialAmount {
	String projectName;
	double financialAmount;

	public String toString() {
		return projectName + financialAmount;
	}
}
