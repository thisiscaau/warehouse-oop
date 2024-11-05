package controller.Supplier;

import au.edu.uts.ap.javafx.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;
import model.Exceptions.*;
import java.util.logging.*;

public class ManageProductsController extends Controller<Supplier>{

    @FXML private Button removeBtn;
    @FXML private Button delistBtn;
    @FXML private ListView<Product> productsLv;

    private ObservableList<Product> getAllProducts(){
        return model.getProducts().getAllProducts();
    }

    @FXML
    public void initialize(){
        productsLv.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldProduct, newProduct) -> removeBtn.setDisable(newProduct == null)
        );
        productsLv.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldProduct, newProduct) -> {
                if (newProduct != null){
                    delistBtn.setDisable(!newProduct.isAvailable());
                }
                else{
                    delistBtn.setDisable(true);
                }
            }
        );

        productsLv.setItems(getAllProducts());
        productsLv.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @FXML
    public void remove(){
        Product selectedProduct = productsLv.getSelectionModel().getSelectedItem();
        getAllProducts().remove(selectedProduct);
    }

    @FXML
    public void delist(){
        Product selectedProduct = productsLv.getSelectionModel().getSelectedItem();
        selectedProduct.delist();
        delistBtn.setDisable(true);
    }

    @FXML
    public void close(){
        stage.close();
    }

}

