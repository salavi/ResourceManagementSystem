package controller.userAccount;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SignupSuccessMessageController {
	@FXML private Text message;
	@FXML private Button enterButton;
	
	public SignupSuccessMessageController(){

	}
	
	@FXML
	public void handleEnterButton(){
		try{
			Stage stage; 
			Parent root;
			stage = (Stage) enterButton.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("../../view/userAccount/LoginUI.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
