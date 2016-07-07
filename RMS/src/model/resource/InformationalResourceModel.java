package model.resource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity  
@Table(name="InformationalResource")  
@PrimaryKeyJoinColumn(name="ID")
public class InformationalResourceModel extends ResourceModel{
	
	private String type;
	
	public String getType() {
		return this.type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
}
