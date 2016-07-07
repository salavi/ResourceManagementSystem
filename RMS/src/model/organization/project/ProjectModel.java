package model.organization.project;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Project")
public class ProjectModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name = "id")
	private Long id;
	
	private String name;
	private int numOfInvolvedHumans;
	private int numOfModules;
	private ProcessModel developementProcess;
	private ProcessModel maintananceProcess;
	private List<TechnologyModel> listOfTechnologies;
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getNumOfInvolvedHumans() {
		return this.numOfInvolvedHumans;
	}
	
	public void setNumOfInvolvedHumans(int numOfInvolvedHumans) {
		this.numOfInvolvedHumans = numOfInvolvedHumans;
	}
	
	public int getNumOfModules() {
		return this.numOfModules;
	}
	
	public void setNumOfModules(int numOfModules) {
		this.numOfModules= numOfModules;
	}
	
	public ProcessModel getDevelopementProcess() {
		return this.developementProcess;
	}
	
	public void setDevelopementProcess(ProcessModel developementProcess) {
		this.developementProcess = developementProcess;
	}
	
	public ProcessModel getMaintananceProcess() {
		return this.maintananceProcess;
	}
	
	public void setMaintananceProcess(ProcessModel maintananceProcess) {
		this.maintananceProcess = maintananceProcess;
	}
	
	public List<TechnologyModel> getListOfTechnologies() {
		return this.listOfTechnologies;
	}
	
	public void setListOfTechnologies(List<TechnologyModel> listOfTechnologies) {
		this.listOfTechnologies = listOfTechnologies;
	}
}
