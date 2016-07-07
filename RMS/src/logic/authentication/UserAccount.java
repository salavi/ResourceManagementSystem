package logic.authentication;

import model.authentication.UserAccountAdapter;
import model.authentication.UserAccountModel;

public class UserAccount {
	private UserAccountModel userAccountModel;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private Integer accessLevel = 0;
	private Boolean isAdmin = false;

	public UserAccount(String firstName, String lastName, String username, String password) {
		setFirstName(firstName);
		setLastName(lastName);
		setUsername(username);
		setPassword(password);
	}

	public int editFirstLastName(String firstName, String lastName){
//		if(firstName == null){
//			
//		}
		this.setLastName(lastName);
		this.setFirstName(firstName);
		userAccountModel.setLastName(lastName);
		userAccountModel.setFirstName(firstName);
		UserAccountAdapter userAccountAdapter = UserAccountAdapter.getInstance();
		return userAccountAdapter.updateFirstLastName(userAccountModel);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(int accessLevel) {
		this.accessLevel = accessLevel;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String toString(){
		return firstName + " " + lastName + " " + username + " " + password + " " + accessLevel;
	}

	public UserAccountModel getUserAccountModel() {
		return userAccountModel;
	}

	public void setUserAccountModel(UserAccountModel userAccountModel) {
		this.userAccountModel = userAccountModel;
	}
}
