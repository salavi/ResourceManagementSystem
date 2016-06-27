package controller;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.DBInitializatorSingleton;
import view.LoginController;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	private DBInitializatorSingleton dbInit;

	/**
	 * Constructor
	 */
	public MainApp() {

	}

	/**
	 * This method initializes UI and DB
	 */
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("به سامانه مدیریت منابع سازمانی خوش آمدید.");


		initLoginLayout();
		showLogin();
		dbInit = new DBInitializatorSingleton();
		dbInit.initial();
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
			loader.setLocation(MainApp.class.getResource("../view/LoginUI.fxml"));
			AnchorPane login = (AnchorPane) loader.load();

			// Set login into the center of login sign up root layout.
			rootLayout.setCenter(login);

			// Give the controller access to the main app.
			LoginController controller = loader.<LoginController>getController();
			controller.setMainApp(this);

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


	public DBInitializatorSingleton getDbInit() {
		return dbInit;
	}


	public void setDbInit(DBInitializatorSingleton dbInit) {
		this.dbInit = dbInit;
	}
}
