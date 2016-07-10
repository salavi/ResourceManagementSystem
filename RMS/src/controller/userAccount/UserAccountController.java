package controller.userAccount;


import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import logic.authentication.Authentication;


public class UserAccountController {
	@FXML private ProfileController profileController;
 	@FXML private EditProfileController editProfileController;
    @FXML private ChangePasswordController changePasswordController;
    @FXML private ChangeAccessLevelController changeAccessLevelController;
	@FXML private RemoveAccountController removeAccountController;
	
	@FXML private TabPane tabPane;
	@FXML private Tab profileTab;
	@FXML private Tab editProfileTab;
	@FXML private Tab changePasswordTab;
	@FXML private Tab changeAccessLevelTab;
	@FXML private Tab removeAccountTab;
	
		
    private Authentication auth;
	
	public UserAccountController() {
//		super();
		auth = Authentication.getInstance();
//		profileController.setAllLabels();
	}
	
	@FXML public void handleProfileTab(){
		if(profileTab.isSelected()){
			profileController.setAllLabels();
		}
	}
	
	@FXML public void handleEditProfileTab(){
		if(editProfileTab.isSelected()){
			editProfileController.firstName.clear();
			editProfileController.lastName.clear();
			editProfileController.message.setText("");
		}
	}
	
	@FXML public void handleChangePasswordTab(){
		if(changePasswordTab.isSelected()){
			changePasswordController.oldPassword.clear();
			changePasswordController.newPassword.clear();
			changePasswordController.newPasswordConfirmation.clear();
			changePasswordController.message.setText("");
		}
	}
	
	@FXML public void handleChangeAccessLevelTab(){
		if(changeAccessLevelTab.isSelected()){
			changeAccessLevelController.username.clear();
			changeAccessLevelController.message.setText("");
			changeAccessLevelController.uppestLevelButton.setSelected(false);
			changeAccessLevelController.mediumLevelButton.setSelected(false);
			changeAccessLevelController.lowestLevelButton.setSelected(false);
		}
		
	}
	
	@FXML public void handleRemoveAccountTab(){
		if(removeAccountTab.isSelected()){
			removeAccountController.username.clear();
			removeAccountController.message.setText("");
		}
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
