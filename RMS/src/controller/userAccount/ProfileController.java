package controller.userAccount;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import logic.authentication.UserAccount;

public class ProfileController {
	@FXML Label firstName;
	@FXML Label lastName;
	@FXML Label username;
	@FXML Label accessLevel;
	
	private UserAccount loggedInUser;
	
	public void initialize(){

	}

	public UserAccount getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(UserAccount loggedInUser) {
		this.loggedInUser = loggedInUser;
	}
	
	public void setAllLabels(){
		firstName.setText(loggedInUser.getFirstName());
		lastName.setText(loggedInUser.getLastName());
		username.setText(loggedInUser.getUsername());
		accessLevel.setText(loggedInUser.getAccessLevel().toString());
	}
}


//FXMLLoader loader = new FXMLLoader();
//loader.setLocation(getClass().getResource("../../view/userAccount/Profile.fxml"));
//root = (Parent) loader.load();
//ProfileController profileController = loader.<ProfileController>getController();
//profileController.setLoggedInUser(loggedInUser);
//profileController.setAllLabels();
