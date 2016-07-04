package controller.userAccount;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class EditProfileController {
	@FXML Button registerButton;
	@FXML TextField firstName;
	@FXML TextField lastName;
	
	public void handleRegisterButton(){
		System.out.println("handleRegisterButton");
	}
}
