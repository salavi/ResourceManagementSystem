package controller.userAccount;

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
			message.setText("لطفا نام کاربری را وارد نمایید");
		}
		else{
			int success = auth.getLoggedInUser().removeAccount(username.getText());
			if(success == -1){
				message.setFill(Color.RED);
				message.setText("نام کاربری مورد نظر یافت نشد");
			}
			else if(success == 0){
				message.setFill(Color.RED);
				message.setText("سطح دسترسی شما از کاربر مورد نظر بالاتر نیست و امکان اعمال این تغییر وجود ندارد");
			}
			else if(success == 1){
				message.setFill(Color.GREEN);
				message.setText("کاربر موردنظر حذف شد");
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
