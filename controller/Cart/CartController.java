package controller.Cart;

import au.edu.uts.ap.javafx.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;
import model.Exceptions.*;
import java.util.logging.*;

public class CartController extends Controller<Cart>{

    @FXML private Button addBtn;
    @FXML private TableView<Product> productsTv;

    public final Cart getCart(){
        return model;
    }

    private ObservableList<Product> catalogue;

    @FXML public void initialize(){
        catalogue = getCart().getCatalogue();
        productsTv.setItems(catalogue);
        productsTv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        productsTv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        productsTv.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null){
                addBtn.setDisable(false);
            }
            else{
                addBtn.setDisable(true);
            }
        });
    }

    @FXML
    public void add(){
        ObservableList<Product> selected = productsTv.getSelectionModel().getSelectedItems();
        for (Product p : selected){
            Order order = new Order(p, -1, model);
            try {
               ViewLoader.showStage(order, "/view/Cart/AddToCartView.fxml", "Adding xd", new FixedStage("/image/cart_icon.png")); 
            } catch (Exception e) {
                System.out.println("Failed to open Add to Cart Window");
                System.out.println(e);
            }
        }
        ObservableList<Product> removedProducts = FXCollections.observableArrayList(selected);
        catalogue.removeAll(removedProducts);
    }

    @FXML
    public void view(){
        try {
            ViewLoader.showStage(model, "/view/Cart/ViewCartView.fxml", "View Cart", new FixedStage("/image/cart_icon.png"));
        } catch (Exception e){
            System.out.println("Failed to open Cart View Window");
            System.out.println(e);
        }
    }

    @FXML
    public void checkout(){
        // Updating the profit of the supplier
        model.getSupplier().processCart(model);

        // Archiving the cart to buyer's history
        model.getUser().addPurchase(model);

        stage.close();
    }

    @FXML
    public void close(){
        stage.close();
    }
}

