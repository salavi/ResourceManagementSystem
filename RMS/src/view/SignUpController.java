package view;

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
	@FXML private Text message = new Text();
	
	private Authentication auth;

	
	public SignUpController(){
		auth = new Authentication();
	}
	
	
	/**
	 * Called when there is an action on returnToLoginButton
	 * @throws IOException 
	 */
	@FXML
	private void handleReturnToLoginButton() throws IOException {
		Stage stage; 
		Parent root;
		stage = (Stage) returnToLoginButton.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("LoginUI.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	
	/**
	 * Called when there is an action on signUpButton
	 */
	@FXML
	private void handleSignUpButton() {
		if(!firstName.getText().isEmpty() && !lastName.getText().isEmpty() && !username.getText().isEmpty() && !password.getText().isEmpty()){
			int success = auth.signUp(username.getText(), password.getText(), firstName.getText(), lastName.getText());
			if(success == -1){
				//dar nazar gerefte shode ke tanha halate adame movafaghiyat, mojud budane username ast
				message.setFill(Color.RED);
				message.setText("این نام کاربری موجود می‌باشد");
			}
			else{
				message.setFill(Color.GREEN);
				message.setText("ثبت‌ نام شما با موفقیت انجام شد");
			}
		}
		else{
			message.setFill(Color.RED);
			message.setText("وارد کردن تمامی اطلاعات الزامی است");
		}
	}
}
