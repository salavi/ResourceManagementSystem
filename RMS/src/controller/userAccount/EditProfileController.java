package controller.userAccount;

import java.io.IOException;

import controller.RootLayoutController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.authentication.UserAccount;

public class EditProfileController {
	@FXML
	Button registerButton;
	@FXML
	TextField firstName;
	@FXML
	TextField lastName;
	@FXML
	Text message = new Text();

	private UserAccount loggedInUser;

	public void handleRegisterButton() {
		if (firstName.getText().isEmpty() && lastName.getText().isEmpty()) {
			message.setFill(Color.RED);
			message.setText("لطفا اطلاعات را برای ویرایش وارد نمایید");
		} else {
			// TODO agar yeki az field ha khali bud
			int success = loggedInUser.editFirstLastName(firstName.getText(), lastName.getText());
			if (success == 1) {
				// message.setFill(Color.GREEN);
				// message.setText("اطلاعات وارد شده با موفقیت ویرایش شد");

				// try {
				// Stage stage;
				// Parent root;
				// stage = (Stage) registerButton.getScene().getWindow();
				// FXMLLoader loader = new FXMLLoader();
				// loader.setLocation(getClass().getResource("../../view/userAccount/Profile.fxml"));
				// root = (Parent) loader.load();
				// ProfileController profileController =
				// loader.<ProfileController>getController();
				// //TODO all loggedInUsers should be updated
				// profileController.setLoggedInUser(loggedInUser);
				// profileController.setAllLabels();
				// Scene scene = new Scene(root);
				// stage.setScene(scene);
				// stage.show();
				// } catch (IOException e) {
				// e.printStackTrace();
				// }

				showHomePage();
			} else if (success == -1) {
				message.setFill(Color.RED);
				message.setText("ویرایش اطلاعات ناموفق بود");
			}
		}
	}
	
	private void showHomePage() {
		try {
			Stage stage;
			Parent root;
			stage = (Stage) registerButton.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("../../view/RootLayout.fxml"));
			root = (Parent) loader.load();
			RootLayoutController rootLayoutController = loader.<RootLayoutController> getController();
			rootLayoutController.setLoggedInUser(loggedInUser);
			UserAccountController userAccountController = rootLayoutController.getUserAccountController();
			userAccountController.setLoggedInUser(loggedInUser);
			userAccountController.getProfileController().setLoggedInUser(loggedInUser);
			userAccountController.getProfileController().setAllLabels();
			userAccountController.getEditProfileController().setLoggedInUser(loggedInUser);

			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public UserAccount getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(UserAccount loggedInUser) {
		this.loggedInUser = loggedInUser;
	}
}
