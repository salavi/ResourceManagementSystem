package controller.resourceManagement;

public enum ResourceType {
	INFORMATIONALRESOURCE("منبع اطلاعاتی"),
	PHYSICALRESOURCE("منبع فیزیکی"),
	HUMANRESOUCE("منبع انسانی"),
	FINANCIALRESOURCE("منبع مالی");
	
	private final String type;
	
	ResourceType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
