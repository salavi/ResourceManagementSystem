package controller.userAccount;

import controller.ErrorMessage;
import controller.SuccessMessage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import logic.authentication.Authentication;

public class RemoveAccountController {
	@FXML TextField username;
	@FXML Button removeButton;
	@FXML Text message;
	
	private Authentication auth;

	public RemoveAccountController() {
		auth = Authentication.getInstance();
		message = new Text();
	}
	
	@FXML public void handleRemoveButton(){
		if(username.getText().isEmpty()){
			message.setFill(Color.RED);
			message.setText(ErrorMessage.ENTER_USERNAME);
		}
		else{
			int success = auth.getLoggedInUser().removeAccount(username.getText());
			if(success == -1){
				message.setFill(Color.RED);
				message.setText(ErrorMessage.USERNAME_NOT_FOUND);
			}
			else if(success == 0){
				message.setFill(Color.RED);
				message.setText(ErrorMessage.LOWER_ACCESS_LEVEL);
			}
			else if(success == 1){
				message.setFill(Color.GREEN);
				message.setText(SuccessMessage.REMOVE_USER);
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
