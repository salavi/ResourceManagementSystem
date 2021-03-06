package controller.userAccount;

import controller.ErrorMessage;
import controller.SuccessMessage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import logic.authentication.Authentication;

public class ChangeAccessLevelController {
	@FXML TextField username;
	@FXML RadioButton uppestLevelButton;
	@FXML RadioButton mediumLevelButton;
	@FXML RadioButton lowestLevelButton;
	@FXML Button enterButton;
	@FXML Text message;

	private Authentication auth;

	public ChangeAccessLevelController() {
		message = new Text();
		auth = Authentication.getInstance();
	}

	@FXML public void handleEnterButton(){
		if(username.getText().isEmpty()){
			message.setFill(Color.RED);
			message.setText(ErrorMessage.ENTER_USERNAME);
		}
		else if(!uppestLevelButton.isSelected() && !mediumLevelButton.isSelected() && !lowestLevelButton.isSelected()){
			message.setFill(Color.RED);
			message.setText(ErrorMessage.ENTER_ACCESS_LEVEL);
		}
		else{
			message.setText("");
			int newAccessLevel = -1;
			if(uppestLevelButton.isSelected()){
				newAccessLevel = 2;
			}
			else if(mediumLevelButton.isSelected()){
				newAccessLevel = 1;
			}
			else if(lowestLevelButton.isSelected()){
				newAccessLevel = 0;
			}
			int success = auth.getLoggedInUser().changeAccesssLevel(username.getText(), newAccessLevel);
			if(success == -1){
				message.setFill(Color.RED);
				message.setText(ErrorMessage.USERNAME_NOT_FOUND);
			}
			else if(success == 0){
				message.setFill(Color.RED);
				message.setText(ErrorMessage.LOWER_ACCESS_LEVEL_MODIFY);
			}
			else if(success == 1){
				message.setFill(Color.GREEN);
				message.setText(SuccessMessage.CHANGE_ACCESS_LEVEL);
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
