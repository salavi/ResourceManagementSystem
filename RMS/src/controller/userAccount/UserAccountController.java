package controller.userAccount;


import java.io.IOException;

import javafx.fxml.FXML;

import logic.authentication.UserAccount;


public class UserAccountController {
	@FXML private ProfileController profileController;
 	@FXML private EditProfileController editProfileController;
    @FXML private ChangePasswordController changePasswordController;
    @FXML private ChangeAccessLevelController changeAccessLevelController;
	@FXML private RemoveAccountController removeAccountController;

    
    public RemoveAccountController getRemoveAccountController() {
		return removeAccountController;
	}

	public void setRemoveAccountController(RemoveAccountController removeAccountController) {
		this.removeAccountController = removeAccountController;
	}

	public ChangeAccessLevelController getChangeAccessLevelController() {
		return changeAccessLevelController;
	}

	public void setChangeAccessLevelController(ChangeAccessLevelController changeAccessLevelController) {
		this.changeAccessLevelController = changeAccessLevelController;
	}
        
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
	
}
