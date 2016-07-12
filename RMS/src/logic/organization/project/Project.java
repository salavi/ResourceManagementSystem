package logic.organization.project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.organization.module.Module;
import model.organization.project.ProcessModel;
import model.organization.project.ProjectAdapter;
import model.organization.project.ProjectModel;
import model.organization.project.TechnologyModel;
import net.sf.cglib.core.ProcessArrayCallback;

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
		maintenanceProcess= new Process("maintenanceProcess");
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

	public void setMaintenanceProcess(Process maintenanceProcess) {
		this.maintenanceProcess = maintenanceProcess;
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

	public Project[] findSimilarProjects(Project project) {
		// TODO
		return null;
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
	
	public void addDevelopementProcess(Long projectId, Long unitId, String activiy, String moduleName, String moduleId, LocalDate startDate, LocalDate endDate) {
		ProjectAdapter projectAdapter = ProjectAdapter.getInstance();
		projectModel =  projectAdapter.getProject(projectId);
		
		Module module = new Module();
		module.setModuleId(moduleId);
		module.setName(moduleName);
		
		developmentProcess.setProcessModel(projectModel.getDevelopementProcess());
		developmentProcess.addActivity(activiy, module, unitId, startDate, endDate);
		
		projectAdapter.addProject(projectModel);
	}
	
	
}
