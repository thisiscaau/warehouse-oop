package controller.Cart;

import au.edu.uts.ap.javafx.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;
import model.Exceptions.*;
import java.util.logging.*;

public class AddToCartController extends Controller<Order>{

    @FXML private TextField quantityTf;
    @FXML private Label productLbl;

    @FXML
    public void initialize(){
        productLbl.setText("Adding " + model.getProduct().getName());
    }

    @FXML
    public void add(){
        try {
            Integer quantity = Integer.parseInt(quantityTf.getText());
            model.setQuantity(quantity);
            model.getCart().addOrder(model);
            stage.close();
        } catch (InvalidQuantityException e) {
            try {
                ViewLoader.showStage(new ErrorModel(e, "Invalid stock entered"), "/view/ErrorView.fxml", "Error", new FixedStage("/image/error_icon.png"));
            }
            catch (Exception ex){
                System.out.println("Failed to open error window");
            }
        }
        catch (NumberFormatException e){
            try {
                ViewLoader.showStage(new ErrorModel(e, "Invalid quantity entered"), "/view/ErrorView.fxml", "Error", new FixedStage("/image/error_icon.png"));
            }
            catch (Exception ex){
                System.out.println("Failed to open error window");
            }
        }
        catch (Exception e){
            System.out.println("Failed to add order");
        }
    }
    
}

