package logic.organization.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public List<ProjectModel> getAllProjects() {
		ProjectAdapter projectAdapter = new ProjectAdapter();
		List<ProjectModel> projectModels = projectAdapter.findAll();
//		List<RetrievedProject> retrievedProject = new ArrayList<>();
//		int counter = 0;
//		for (ProjectModel projectModel : projectModels) {
//			System.out.println(Integer.toString(counter) + projectModel.getName() + " " + projectModel.getId());
//			RetrievedProject rp = new RetrievedProject(projectModel.getName(),)
//			map.put(Integer.toString(counter) + projectModel.getName(), projectModel.getId());
//			++counter;
//			
//		}
		return projectModels;
	}

}



