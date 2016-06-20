package controller;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("سامانه‌ی مدیریت منابع سازمانی");

		initLoginLayout();
		showLogin();

	}

	private void initLoginLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("../view/LoginSignUpRootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Show the scene containing the login sign up root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void showLogin() {
		try {
            // Load login.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/Login.fxml"));
            AnchorPane login = (AnchorPane) loader.load();

            // Set login into the center of login sign up root layout.
            rootLayout.setCenter(login);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public Stage getPrimaryStage() {
        return primaryStage;
    }

	public static void main(String[] args) {
		launch(args);
	}
}
