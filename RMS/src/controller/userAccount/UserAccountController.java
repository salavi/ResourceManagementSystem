package controller.userAccount;


import javafx.fxml.FXML;
import logic.authentication.Authentication;


public class UserAccountController {
	@FXML private ProfileController profileController;
 	@FXML private EditProfileController editProfileController;
    @FXML private ChangePasswordController changePasswordController;
    @FXML private ChangeAccessLevelController changeAccessLevelController;
	@FXML private RemoveAccountController removeAccountController;
	
    private Authentication auth;
	
	public UserAccountController() {
		auth = Authentication.getInstance();
	}
	
	public Authentication getAuth() {
		return auth;
	}

	public void setAuth(Authentication auth) {
		this.auth = auth;
	}

	public ProfileController getProfileController() {
		return profileController;
	}

	public void setProfileController(ProfileController profileController) {
		this.profileController = profileController;
	}

	public EditProfileController getEditProfileController() {
		return editProfileController;
	}

	public void setEditProfileController(EditProfileController editProfileController) {
		this.editProfileController = editProfileController;
	}

	public ChangePasswordController getChangePasswordController() {
		return changePasswordController;
	}

	public void setChangePasswordController(ChangePasswordController changePasswordController) {
		this.changePasswordController = changePasswordController;
	}
	
	public ChangeAccessLevelController getChangeAccessLevelController() {
		return changeAccessLevelController;
	}

	public void setChangeAccessLevelController(ChangeAccessLevelController changeAccessLevelController) {
		this.changeAccessLevelController = changeAccessLevelController;
	}
    
	public RemoveAccountController getRemoveAccountController() {
		return removeAccountController;
	}

	public void setRemoveAccountController(RemoveAccountController removeAccountController) {
		this.removeAccountController = removeAccountController;
	}
	
}
