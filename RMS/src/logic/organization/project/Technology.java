package logic.organization.project;

public class Technology {
	
	private String name;
	private String goalOfUsage;
	
	public Technology(){
		
	}
	
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

//	public Map<String, Long> getAllTechnologies() {
//		TechnologyAdapter technologyAdapter = TechnologyAdapter.getInstance();
//		List<TechnologyModel> technologyModels = technologyAdapter.findAll();
//		Map<String, Long> technologyNameIdMap = new HashMap<>();
//		int counter = 1;
//		for (TechnologyModel technologyModel : technologyModels) {
//			technologyNameIdMap.put(counter + "." + technologyModel.getName(), technologyModel.getId());
//			counter++;
//		}	
//		return technologyNameIdMap;
//	}
}
