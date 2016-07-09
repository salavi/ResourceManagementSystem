package controller.userAccount;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import logic.authentication.Authentication;

public class ProfileController {
	@FXML Label firstName;
	@FXML Label lastName;
	@FXML Label username;
	@FXML Label accessLevel;
	
	private Authentication auth;
	
	
	public ProfileController() {
		auth = Authentication.getInstance();
	}

	public void setAllLabels(){
		firstName.setText(auth.getLoggedInUser().getFirstName());
		lastName.setText(auth.getLoggedInUser().getLastName());
		username.setText(auth.getLoggedInUser().getUsername());
		accessLevel.setText(auth.getLoggedInUser().getAccessLevel().toString());
	}

	public Authentication getAuth() {
		return auth;
	}

	public void setAuth(Authentication auth) {
		this.auth = auth;
	}
}

