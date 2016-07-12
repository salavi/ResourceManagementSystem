package controller.resourceManagement;

public enum ResourceType {
	INFORMATIONALRESOURCE("منبع اطلاعاتی", "Information"),
	PHYSICALRESOURCE("منبع فیزیکی", "Physical"),
	HUMANRESOUCE("منبع انسانی", "Human"),
	FINANCIALRESOURCE("منبع مالی", "Financial");
	
	private final String farsi_type;
	private final String english_type;
	
	ResourceType(String f_type, String en_type) {
		this.farsi_type = f_type;
		this.english_type = en_type;
	}

	
	public String getFarsi_Type() {
		return farsi_type;
	}

	public String getEnglish_type() {
		return english_type;
	}
}
