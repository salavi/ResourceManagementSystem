package controller.userAccount;

import controller.ErrorMessage;
import controller.SuccessMessage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import logic.authentication.Authentication;

public class EditProfileController {
	@FXML
	Button registerButton;
	@FXML
	TextField firstName;
	@FXML
	TextField lastName;
	@FXML
	Text message = new Text();

	private Authentication auth;

	public EditProfileController() {
		auth = Authentication.getInstance();
	}

	public void handleRegisterButton() {
		if (firstName.getText().isEmpty() && lastName.getText().isEmpty()) {
			message.setFill(Color.RED);
			message.setText(ErrorMessage.INVALID_INPUT);
		} else {
			int success;
			if(firstName.getText().isEmpty()){
				success = auth.getLoggedInUser().editFirstLastName("", lastName.getText());
			}
			else if(lastName.getText().isEmpty()){
				success = auth.getLoggedInUser().editFirstLastName(firstName.getText(), "");
			}
			else{
				success = auth.getLoggedInUser().editFirstLastName(firstName.getText(), lastName.getText());
			}
			if (success == 1) {
				message.setFill(Color.GREEN);
				message.setText(SuccessMessage.EDIT_INFO);
			} else if (success == -1) {
				message.setFill(Color.RED);
				message.setText(ErrorMessage.EDIT_PROFILE);
			}
		}
	}


	public Authentication getAuth() {
		return auth;
	}

	public void setAuth(Authentication auth) {
		this.auth = auth;
	}

}
