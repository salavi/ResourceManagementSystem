package controller.userAccount;

import java.io.IOException;

import controller.ErrorMessage;
import controller.MainApp;
import controller.SuccessMessage;
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
import logic.authentication.UserAccount;

public class LoginController {	  
	private MainApp mainApp; // Reference to the main application.
	@FXML private TextField username;
	@FXML private TextField password;
	@FXML private Button loginButton;
	@FXML private Button signUpButton;
	@FXML private Text message;

	private Authentication auth;
	private UserAccount loggedInUser;


	/**
	 * The constructor.
	 * The constructor is called before the initialize() method.
	 */
	public LoginController() {
		auth = Authentication.getInstance();
		message  = new Text();
	}



	/**
	 * Called when there is an action on LoginButton
	 */
	@FXML
	private void handleLoginButton() {
		if(!username.getText().isEmpty() && !password.getText().isEmpty()){
			loggedInUser = auth.login(username.getText(), password.getText());
			if(loggedInUser == null){
				message.setFill(Color.RED);
				message.setText(ErrorMessage.USER_NOT_FOUND);
			}
			else{
				//TODO
				message.setFill(Color.GREEN);
				message.setText(SuccessMessage.LOGIN);
				showHomePage();
			}
		}
		else{
			message.setFill(Color.RED);
			message.setText(ErrorMessage.ENTER_USERNAME_PASS);
		}

	}
	
	private void showHomePage(){
		try {
			Stage stage;
			Parent root;
			stage = (Stage) loginButton.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/RootLayout.fxml"));
			root = (Parent) loader.load();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Called when there is an action on SignUpButton
	 * @throws IOException 
	 */
	@FXML
	private void handleSignupButton() throws IOException {
		Stage stage; 
		Parent root;
		stage = (Stage) signUpButton.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("/view/userAccount/SignUpUI.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}


	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
}
