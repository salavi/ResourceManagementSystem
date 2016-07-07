package model.unit;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import model.resource.ResourceModel;

@Entity
@Table(name = "Unit")

public class UnitModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	@Column(name = "id")
	private Long id;
	
	private String unitId;
	private String specialty;
	private Set<ResourceModel> requiredResources;
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUnitId() {
		return this.unitId;
	}
	
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	
	public String getSpecialty() {
		return this.specialty;
	}
	
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	
	public Set<ResourceModel> getRequiredResources() {  
	    return this.requiredResources; 
	} 
	
	public void setRequiredResources(Set<ResourceModel> requiredResources) {  
	    this.requiredResources = requiredResources;  
	} 
}

