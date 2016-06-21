package controller;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.UserAccountModel;
import view.LoginController;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	/**
     * The data as an observable list of UserAccounts.
     */
    private ObservableList<UserAccountModel> accountsData = FXCollections.observableArrayList();

    /**
     * Constructor
     */
    public MainApp() {
        // Add some sample data
        accountsData.add(new UserAccountModel("Hoorieh", "Marefat", "hoooooori", "pass1"));
        accountsData.add(new UserAccountModel("Mehrnush", "Mirtaheri", "makhmoosh", "pass2"));
        accountsData.add(new UserAccountModel("Soheil", "Alavi", "salavi", "pass3"));
//        accountData.add(new UserAccount("Cornelia", "Meier"));
//        accountData.add(new UserAccount("Werner", "Meyer"));
//        accountData.add(new UserAccount("Lydia", "Kunz"));
//        accountData.add(new UserAccount("Anna", "Best"));
//        accountData.add(new UserAccount("Stefan", "Meier"));
//        accountData.add(new UserAccount("Martin", "Mueller"));
    }

    /**
     * Returns the data as an observable list of UserAccounts. 
     * @return
     */
    public ObservableList<UserAccountModel> getaccountData() {
        return accountsData;
    }
	
	

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("سامانه‌ مدیریت منابع سازمانی");

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
}
