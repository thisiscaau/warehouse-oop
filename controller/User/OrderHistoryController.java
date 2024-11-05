package controller.User;

import au.edu.uts.ap.javafx.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;
import model.Exceptions.*;
import java.util.logging.*;

public class OrderHistoryController extends Controller<User>{

    @FXML private ListView<Cart> orderHistoryLv;

    @FXML
    public void initialize(){
        orderHistoryLv.setItems(model.getPurchaseHistory());
    }

    @FXML
    public void close(){
        stage.close();
    }
}

