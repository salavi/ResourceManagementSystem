package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "Resource")
@Inheritance(strategy = InheritanceType.JOINED)

public class ResourceModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	@Column(name = "id")
	private Long id;

	private String resourceId;
	private UnitModel currentUnit;

	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	
	public String getResourceId() {
		return this.resourceId;
	}
	
	public void setResourceId(String resourceID) {
		this.resourceId = resourceID;
	}
	
	public UnitModel getCurrentUnit() {
		return this.currentUnit;
	}
	
	public void setCurrentUnit(UnitModel currentUnit) {
		this.currentUnit = currentUnit;
	}
	
}