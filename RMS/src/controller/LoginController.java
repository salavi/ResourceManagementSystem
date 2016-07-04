package controller;

import java.io.IOException;

import controller.MainApp;
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
		auth = new Authentication();
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
				message.setText("Ú©Ø§Ø±Ø¨Ø± Ø¨Ø§ Ø§ÛŒÙ† Ù…Ø´Ø®ØµØ§Øª Ù…ÙˆØ¬ÙˆØ¯ Ù†Ù…ÛŒâ€ŒØ¨Ø§Ø´Ø¯");
			}
			else{
				//TODO
				message.setFill(Color.GREEN);
				message.setText("ÙˆØ±ÙˆØ¯ Ø´Ù…Ø§ Ø¨Ø§ Ù…ÙˆÙ�Ù‚ÛŒØª Ø§Ù†Ø¬Ø§Ù… Ø´Ø¯");
				try{
					Stage stage; 
					Parent root;
					stage = (Stage) loginButton.getScene().getWindow();
					root = FXMLLoader.load(getClass().getResource("../view/UserAccount.fxml"));
					Scene scene = new Scene(root);
					stage.setScene(scene);
					stage.show();
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		else{
			message.setFill(Color.RED);
			message.setText("Ù„Ø·Ù�Ø§ Ù†Ø§Ù… Ú©Ø§Ø±Ø¨Ø±ÛŒ Ùˆ Ø±Ù…Ø² Ø¹Ø¨ÙˆØ± Ø±Ø§ ÙˆØ§Ø±Ø¯ Ù†Ù…Ø§ÛŒÛŒØ¯");
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
		root = FXMLLoader.load(getClass().getResource("../view/SignUpUI.fxml"));
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
