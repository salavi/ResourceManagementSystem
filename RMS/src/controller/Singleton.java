package controller;

//import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
//import javafx.scene.Scene;
import javafx.stage.Stage;

public class Singleton {
	private static Singleton instance = null;
	
	private Singleton(){
		
	}
	
	public static Singleton getInstance(){
		if(instance.equals(null)){
			instance = new Singleton();
			return instance;
		}
		return instance;
	}
	
	public void redirect(Stage stage, Parent root){
//		try{
//			Stage stage; 
//			Parent root;
//			stage = (Stage) returnToLoginButton.getScene().getWindow();
//			root = FXMLLoader.load(getClass().getResource("../view/LoginUI.fxml"));
//			Scene scene = new Scene(root);
//			stage.setScene(scene);
//			stage.show();
//		}
//		catch(Exception e){
//			e.printStackTrace();
//		}
	}
}
