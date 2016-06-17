package model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UserAccount {

	private StringProperty firstName;
	private StringProperty lastName;
	private StringProperty username;
	private StringProperty password;
	private IntegerProperty accessLevel;
	private BooleanProperty isAdmin;

	public UserAccount(String firstName, String lastName, String username, String password) {

		setFirstName(firstName);
		setLastName(lastName);
		setUsername(username);
		setPassword(password);
	}

	public StringProperty getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = new SimpleStringProperty(firstName);
	}

	public StringProperty getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = new SimpleStringProperty(lastName);
	}

	public StringProperty getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = new SimpleStringProperty(username);
	}

	public StringProperty getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = new SimpleStringProperty(password);
	}

	public IntegerProperty getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(int accessLevel) {
		this.accessLevel = new SimpleIntegerProperty(accessLevel);
	}

	public BooleanProperty getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = new SimpleBooleanProperty(isAdmin);
	}

}
