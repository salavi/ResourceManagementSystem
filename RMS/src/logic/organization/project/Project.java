package logic.organization.project;

import java.util.ArrayList;

public class Project {

	private String name;
	private int numOfInvolvedHumans;
	private int numOfModules;
	private Process developmentProcess;
	private Process maintenanceProcess;
	private ArrayList<Technology> listOfTechnologies;

	public Project(String name, int numOfHumans, int numOfModules, Process developmentProcess,
			Process maintananceProcess, ArrayList<Technology> listOfTechnologies) {
		this.setName(name);
		this.setNumOfInvolvedHumans(numOfHumans);
		this.setNumOfModules(numOfModules);
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
	
	public void addProjectToDB(){
		//TODO
	}

}
