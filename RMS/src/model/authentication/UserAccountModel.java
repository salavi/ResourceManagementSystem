package model.authentication;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import logic.authentication.UserAccount;


@Entity
//@Table(name = "UserAccounts", 
//uniqueConstraints = @UniqueConstraint(columnNames = {"UserName"}))
public class UserAccountModel {
	private String firstName;
	private String lastName;	
	//@Column(unique = true)	
	private String username;	
	private String password;
	private Integer accessLevel = 0;
	private Boolean isAdmin = false;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;  

	
	public UserAccountModel(String firstName, String lastName, String username, String password) {
		setFirstName(firstName);
		setLastName(lastName);
		setUsername(username);
		setPassword(password);
	}
	
	public UserAccountModel(){
		
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

	public Long getId() {  
	    return id;  
	}  
	
	public void setId(Long id) {  
	    this.id = id;  
	}  
	
	public String toString(){
		return firstName + " " + lastName + " " + username + " " + password;
	}
	
	public boolean authenticate(String username, String password){
		return this.username.equals(username) && this.password.equals(password);
	}
	
	public UserAccount getUserAccount(){
		UserAccount result = new UserAccount(firstName, lastName, username, password);
		result.setUserAccountModel(this);
		return result;
	}
}
