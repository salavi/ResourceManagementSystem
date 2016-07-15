package controller.userAccount;

import controller.ErrorMessage;
import controller.SuccessMessage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import logic.authentication.Authentication;

public class ChangePasswordController {
	@FXML 
	PasswordField oldPassword;
	@FXML 
	PasswordField newPassword;
	@FXML 
	PasswordField newPasswordConfirmation;
	@FXML 
	Button enterButton;
	@FXML 
	Text message = new Text();
	
	private Authentication auth;
	
	public ChangePasswordController() {
		auth = Authentication.getInstance();
	}

	@FXML public void handleEnterButton(){
		if (oldPassword.getText().isEmpty() || newPassword.getText().isEmpty() || newPasswordConfirmation.getText().isEmpty()) {
			message.setFill(Color.RED);
			message.setText(ErrorMessage.INVALID_INPUT);
		} else {
			// TODO agar yeki az field ha khali bud
			message.setText("");
			int success = auth.getLoggedInUser().editPassword(oldPassword.getText(), newPassword.getText(), newPasswordConfirmation.getText());
			if (success == 1) {
				message.setFill(Color.GREEN);
				message.setText(SuccessMessage.EDIT_INFO);
			} else if (success == 0) {
				message.setFill(Color.RED);
				message.setText(ErrorMessage.INVALID_OLD_PASS);
			}
			else if(success == 2){
				message.setFill(Color.RED);
				message.setText(ErrorMessage.DIFFERENT_PASS_AND_PASS_CONF);
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
