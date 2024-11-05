package controller.Supplier;

import au.edu.uts.ap.javafx.*;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;
import model.Exceptions.*;
import java.util.logging.*;

public class SupplierCustomerController extends Controller<Supplier>{

    @FXML private Button orderBtn;
    @FXML private TableView<Product> productsTv;

    private ObservableList<Product> getAvailableProducts(){
        return model.getProducts().getAvailableProducts();
    }

    @FXML public void initialize(){
        productsTv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        productsTv.setItems(getAvailableProducts());
        productsTv.setSelectionModel(null);
    }

    public Supplier getSupplier(){
        return model;
    }

    @FXML
    public void order(){
        Cart cart = new Cart(model, Organisation.getLoggedInUser());
        try {
            // Loading the cart window
            ViewLoader.showStage(cart, "/view/Cart/CartView.fxml", "Cart", new FixedStage("/image/cart_icon.png"));
        } catch (Exception e) {
            System.out.println("ngu");
            System.out.println(e);
        }
    }

    @FXML
    public void close(){
        stage.close();
    }
}

