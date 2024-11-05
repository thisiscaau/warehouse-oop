package controller;

import au.edu.uts.ap.javafx.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;
import model.Exceptions.*;
import java.util.logging.*;

public class LoginController extends Controller<Organisation>{

    @FXML private TextField usernameTf;
    @FXML private PasswordField passwordPf;
    @FXML private Button loginBtn;

    @FXML
    public void initialize() {
        loginBtn.disableProperty().bind(usernameTf.textProperty().isEmpty().or(passwordPf.textProperty().isEmpty()));
    }

    @FXML
    public void login(){
        try {
            Users users = model.getUsers();
            User respond = users.validateUser(usernameTf.getText(), passwordPf.getText());
            model.setLoggedInUser(respond);
            try {
                if (model.getLoggedInUser() instanceof Manager){
                    ViewLoader.showStage(model, "/view/User/UserDashboardView.fxml", "Manager Dashboard", new FixedStage("/image/user_icon.png"));
                }
                else{
                    ViewLoader.showStage(model, "/view/User/UserDashboardView.fxml", "Customer Dashboard", new FixedStage("/image/user_icon.png"));
                }
            }
            catch (Exception e){
                System.out.println(e);
            }
            stage.close();
        }
        catch (NoSuchUserException e) {
            try {
                ViewLoader.showStage(new ErrorModel(e, "Invalid credentials"), "/view/ErrorView.fxml", "Error", new FixedStage("/image/error_icon.png"));
            }
            catch (Exception ex){
                System.out.println(ex);
            }
        }
    }

    @FXML
    public void close() {
        stage.close();
    }
}

