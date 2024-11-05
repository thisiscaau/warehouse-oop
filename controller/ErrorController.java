package controller;

import au.edu.uts.ap.javafx.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;
import model.Exceptions.*;
import java.util.logging.*;

public class ErrorController extends Controller<ErrorModel>{

    @FXML private Label exceptionLb;
    @FXML private Label messageLb;

    @FXML
    public void initialize(){
        exceptionLb.setText(model.getException());
        messageLb.setText(model.getMessage());
    }

    @FXML
    public void close(){
        stage.close();
    }
}

