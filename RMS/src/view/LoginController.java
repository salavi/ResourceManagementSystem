package view;

import controller.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import logic.authentication.Authentication;
import logic.authentication.UserAccount;

public class LoginController {
	////////////////////////
	  @FXML private TextField username;
	  @FXML private TextField password;
	  @FXML private Button loginButton;
	  @FXML private Text message = new Text();
	  
	  private Authentication auth;
	  private UserAccount loggedInUser;
	  

	  /**
	   * Check authorization credentials.
	   * 
	   * If accepted, go to the home page
	   * otherwise, return null.
	   */   
//	  private String authorize() {
//	    return 
//	      "open".equals(user.getText()) && "sesame".equals(password.getText()) 
//	            ? generateSessionID() 
//	            : null;
//	  }
	////////////////////////
	  /**
	   * Called when the user clicks on the login button.
	   */
	  @FXML
	  private void handleLoginButton() {
		  if(!username.getText().isEmpty() && !password.getText().isEmpty()){
			  loggedInUser = auth.login(username.getText(), password.getText());
			  if(loggedInUser == null){
				  message.setText("اطلاعات وارد شده نادرست می‌باشد");
			  }
			  else{
				  message.setText("ورود شما با موفقیت انجام شد");
			  }
		  }
		  else{
			  message.setText("لطفا نام کاربری و رمز عبور را وارد نمایید");
		  }
	      
	  }
	  //////////////////////////
	// Reference to the main application.
    private MainApp mainApp;

    
    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public LoginController() {
    	auth = new Authentication();
    }
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
//    @FXML
//    private void initialize() {
//        // Initialize the person table with the two columns.
//        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
//        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
//    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
//        personTable.setItems(mainApp.getPersonData());
    }
    
}
