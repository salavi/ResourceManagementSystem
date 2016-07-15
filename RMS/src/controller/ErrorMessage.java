package controller;

public enum ErrorMessage {
	INVALIDINPUTMESSAGE(""),
	USERDOESNOTEXISTMESSAGE("");
	
	
	private final String text;
	private ErrorMessage(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
}
