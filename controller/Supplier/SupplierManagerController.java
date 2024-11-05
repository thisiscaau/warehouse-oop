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
import javax.swing.text.View;

public class SupplierManagerController extends Controller<Supplier>{

    @FXML private TableView<Product> productsTv;
    @FXML private CheckBox availableCb;

    private ObservableList<Product> getAvailableProducts(){
        return model.getProducts().getAvailableProducts();
    }

    private ObservableList<Product> getAllProducts(){
        return model.getProducts().getAllProducts();
    }

    @FXML public void initialize(){
        productsTv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        productsTv.setItems(getAllProducts());
        productsTv.setSelectionModel(null);

        availableCb.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                productsTv.setItems(getAvailableProducts());
            }else{
                productsTv.setItems(getAllProducts());
            }
        });
    }

    public Supplier getSupplier(){
        return model;
    }

    @FXML
    public void manage(){
        try {
            ViewLoader.showStage(model, "/view/Supplier/ManageProductsView.fxml", "Managing", new FixedStage("/image/supplier_icon.png"));
        } catch (Exception e) {
            System.out.println("Ngu! Failed to open Manage Products Window");
            System.out.println(e);
        }
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

