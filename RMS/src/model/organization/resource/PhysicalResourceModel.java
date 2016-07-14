package model.organization.resource;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity  
@Table(name="PhysicalResource")  
@PrimaryKeyJoinColumn(name="ID")
public class PhysicalResourceModel extends ResourceModel{

	private String type;
	
	public String getType() {
		return this.type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
}
