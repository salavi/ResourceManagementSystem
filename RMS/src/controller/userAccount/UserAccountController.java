package controller.userAccount;


import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import logic.authentication.UserAccount;


public class UserAccountController {
	@FXML private ProfileController profileController;
 	@FXML private EditProfileController editProfileController;
    @FXML private ChangePasswordController changePasswordController;
    @FXML private ChangeAccessLevelController changeAccessLevelController;
    @FXML private RemoveAccountController removeAccountController;
        
    private UserAccount loggedInUser;
	
	public UserAccountController() {
		// TODO Auto-generated constructor stub
	}
	
	public void initialize() throws IOException{
//		FXMLLoader profileLoader = new FXMLLoader();
//		profileLoader.setLocation(getClass().getResource("../../view/userAccount/Profile.fxml"));
//		Parent root = (Parent) profileLoader.load();
//		ProfileController profileController = profileLoader.<ProfileController>getController();
//		profileController.setLoggedInUser(loggedInUser);
//		
//		
//		FXMLLoader editProfileLoader = new FXMLLoader();
//		editProfileLoader.setLocation(getClass().getResource("../../view/userAccount/EditProfile.fxml"));
//		Parent editProfileRoot = (Parent) editProfileLoader.load();
//		EditProfileController editProfileController = editProfileLoader.<EditProfileController>getController();
//		
//		System.out.println("Profile Controller is null: " + (profileController == null));
//		System.out.println("Edit Profile Controller is null: " + (editProfileController == null));
	}
	
	public ProfileController getProfileController() {
		return profileController;
	}


	public void setProfileController(ProfileController profileController) {
		this.profileController = profileController;
	}
	
	public UserAccount getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(UserAccount loggedInUser) {
		this.loggedInUser = loggedInUser;
	}

	public String printUser(){
		return loggedInUser.getFirstName() + " " + loggedInUser.getLastName() + " " + loggedInUser.getUsername() + " " + loggedInUser.getPassword() + " " + loggedInUser.getAccessLevel();
	}
	
}
