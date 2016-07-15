package model.organization.resource;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity  
@Table(name="HumanResource")  
@PrimaryKeyJoinColumn(name="ID") 
public class HumanResourceModel extends ResourceModel{

	private String firstName;
	
	private String lastName;
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}
