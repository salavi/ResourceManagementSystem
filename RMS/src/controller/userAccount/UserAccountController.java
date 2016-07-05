package controller.userAccount;


import javafx.fxml.FXML;
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
	
	public ProfileController getProfileController() {
		return profileController;
	}


	public void setProfileController(ProfileController profileController) {
		this.profileController = profileController;
	}
	
	public void initialize(){
		System.out.println("UserAccountInitialize");
		System.out.println(profileController);
		
	}

	public UserAccount getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(UserAccount loggedInUser) {
		this.loggedInUser = loggedInUser;
	}

	
}
