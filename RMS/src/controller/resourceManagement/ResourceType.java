package controller.resourceManagement;

public enum ResourceType {
	INFORMATIONALRESOURCE("منبع اطلاعاتی", "Information"),
	PHYSICALRESOURCE("منبع فیزیکی", "Physical"),
	HUMANRESOUCE("منبع انسانی", "Human"),
	FINANCIALRESOURCE("منبع مالی", "Financial");
	
	private final String farsiType;
	private final String englishType;
	
	private ResourceType(String fType, String enType) {
		this.farsiType = fType;
		this.englishType = enType;
	}

	
	public String getFarsiType() {
		return farsiType;
	}

	public String getEnglishType() {
		return englishType;
	}
}
