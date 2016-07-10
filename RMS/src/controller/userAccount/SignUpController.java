package controller.userAccount;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.authentication.Authentication;

public class SignUpController {
	@FXML private TextField firstName;
	@FXML private TextField lastName;
	@FXML private TextField username;
	@FXML private TextField password;
	@FXML private Button signUpButton;
	@FXML private Button returnToLoginButton;
	@FXML private Text message;
	
	private Authentication auth;

	
	public SignUpController(){
		auth = Authentication.getInstance();
		message = new Text();
	}
	
	
	/**
	 * Called when there is an action on returnToLoginButton
	 * @throws IOException 
	 */
	@FXML
	private void handleReturnToLoginButton() throws IOException {
		try{
			Stage stage; 
			Parent root;
			stage = (Stage) returnToLoginButton.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("/view/userAccount/LoginUI.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Called when there is an action on signUpButton
	 */
	@FXML
	private void handleSignUpButton() {
		if(!firstName.getText().isEmpty() && !lastName.getText().isEmpty() && !username.getText().isEmpty() && !password.getText().isEmpty()){
			int success = auth.signUp(firstName.getText(), lastName.getText(), username.getText(), password.getText());
			if(success == -1){
				//TODO
				//username is not unique
				message.setFill(Color.RED);
				message.setText("این نام کاربری موجود می‌باشد");
				username.clear();
			}
			else if(success == 1){
				try{
					Stage stage; 
					Parent root;
					stage = (Stage) returnToLoginButton.getScene().getWindow();
					root = FXMLLoader.load(getClass().getResource("/view/userAccount/SignupSuccessMessage.fxml"));
					Scene scene = new Scene(root);
					stage.setScene(scene);
					stage.show();
					Authentication.setInstanceToNull();
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		else{
			message.setFill(Color.RED);
			message.setText("وارد کردن تمامی اطلاعات الزامی است");
		}
	}
}
