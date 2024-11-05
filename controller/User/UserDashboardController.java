package controller.User;

import au.edu.uts.ap.javafx.*;

import java.io.IOException;
import java.lang.classfile.Label;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;
import model.Exceptions.*;
import java.util.logging.*;
import javafx.application.Platform;

public class UserDashboardController extends Controller<Organisation>{
    
    @FXML private Button selectSupplierBtn;
    @FXML private Button orderHistoryBtn;
    @FXML private Label welcomeLbl;

    private User loggedInUser;

    @FXML
    public void initialize() {

        loggedInUser = model.getLoggedInUser();

        selectSupplierBtn.setOnAction(e -> viewSuppliers());

        if (loggedInUser instanceof Customer){
            welcomeLbl.setText("Welcome to the Customer Dashboard " + loggedInUser.getFirstName());
            selectSupplierBtn.setText("Shop");
        }
        else {
            welcomeLbl.setText("Welcome to the Manager Dashboard " + loggedInUser.getFirstName());
            selectSupplierBtn.setText("Manage");
        }
    }

    private void viewSuppliers(){
        try {
            ViewLoader.showStage(model, "/view/Supplier/SupplierListView.fxml", "Supplier List", new FixedStage("/image/supplier_icon.png"));
        } catch (Exception e) {
            System.out.println("ngu");
            //System.out.println(e);
        }
    }

    @FXML
    public void viewOrderHistory(){
        // to be implemented
        try {
            ViewLoader.showStage(loggedInUser, "/view/User/OrderHistoryView.fxml", "Order History", new FixedStage("/image/user_icon.png"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    public void close(){
        Platform.exit();
    }
}

