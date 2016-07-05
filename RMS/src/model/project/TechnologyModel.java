package model.project;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Technology")
public class TechnologyModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	@Column(name = "id")
	private Long id;
	
	private String name;
	private String goalOfUsage;
	
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
	
	public String getGoalOfUsage() {
		return this.goalOfUsage;
	}
	
	public void setGoalOfUsage(String goalOfUsage) {
		this.goalOfUsage = goalOfUsage;
	}
}
