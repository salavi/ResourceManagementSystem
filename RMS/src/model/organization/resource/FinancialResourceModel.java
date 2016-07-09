package model.organization.resource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity  
@Table(name="FinancialResource")  
@PrimaryKeyJoinColumn(name="ID")
public class FinancialResourceModel extends ResourceModel {
	
	private double amount;
	
	public double getAmount() {
		return this.amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
}
