package au.edu.uts.ap.javafx;

import javafx.fxml.*;
import javafx.stage.*;
import model.Exceptions.ErrorModel;
import javafx.scene.*;
import javafx.scene.image.Image;

import java.io.*;
import java.lang.Runnable;
import java.util.logging.Level;
import java.util.logging.Logger;

import controller.LoginController;

public class ViewLoader {

    public final static int X = 5;
    public final static int Y = 5;

    public static <T> void showStage(T model, String fxml, String title, Stage stage, Runnable onStageClosed) throws IOException {
        FXMLLoader loader = new FXMLLoader(Controller.class.getResource(fxml), null, null,
                type -> {
                    try {
                        @SuppressWarnings("unchecked")
                        Controller<T> controller = (Controller<T>) type.newInstance();
                        controller.model = model;
                        controller.stage = stage;
                        return controller;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        Parent root = loader.load();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.sizeToScene();
        stage.show();
    }

    public static <T> void showStage(T model, String fxml, String title, Stage stage) throws IOException {
        showStage(model, fxml, title, stage, () -> {
        });
    }

    public static void showErrorStage(ErrorModel error) {
        try {
            Stage stage = new FixedStage("/image/error_icon.png");
            showStage(error, "/view/ErrorView.fxml", "Error", stage);
        }
        catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex); 
        }
    }
}
