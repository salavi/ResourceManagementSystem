package controller.userAccount;


import javafx.fxml.FXML;


public class UserAccountController {
	@FXML private ProfileController profileController;
 	@FXML private EditProfileController editProfileController;
    @FXML private ChangePasswordController changePasswordController;
    @FXML private ChangeAccessLevelController changeAccessLevelController;
    @FXML private RemoveAccountController removeAccountController;
    
	
	public UserAccountController() {
		// TODO Auto-generated constructor stub
	}
	
	public ProfileController getProfileController() {
		return profileController;
	}


	public void setProfileController(ProfileController profileController) {
		this.profileController = profileController;
	}

	
}
