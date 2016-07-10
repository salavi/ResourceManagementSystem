package logic.organization.project;

public class Technology {
	
	private String name;
	private String goalOfUsage;
	
	public Technology(String name, String goalOfUsage){
		this.setName(name);
		this.setGoalOfUsage(goalOfUsage);
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGoalOfUsage() {
		return goalOfUsage;
	}

	public void setGoalOfUsage(String goalOfUsage) {
		this.goalOfUsage = goalOfUsage;
	}
}
