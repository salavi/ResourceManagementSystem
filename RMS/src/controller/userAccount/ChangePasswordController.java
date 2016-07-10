package controller.userAccount;

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
			message.setText("وارد کردن تمامی اطلاعات جهت ویرایش الزامی است");
		} else {
			// TODO agar yeki az field ha khali bud
			message.setText("");
			int success = auth.getLoggedInUser().editPassword(oldPassword.getText(), newPassword.getText(), newPasswordConfirmation.getText());
			if (success == 1) {
				message.setFill(Color.GREEN);
				message.setText("اطلاعات وارد شده با موفقیت ویرایش شد");
			} else if (success == 0) {
				message.setFill(Color.RED);
				message.setText("رمز عبور قدیمی وارد شده با رمز عبور حساب کاربری یکسان نمی‌باشد");
			}
			else if(success == 2){
				message.setFill(Color.RED);
				message.setText("رمز عبور جدید و تکرار آن متفاوت می‌باشند");
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
