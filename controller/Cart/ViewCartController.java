package controller.Cart;

import au.edu.uts.ap.javafx.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;
import model.Exceptions.*;
import java.util.logging.*;

public class ViewCartController extends Controller<Cart>{

    @FXML private TableColumn<Order, String> productNameColumn;
    @FXML private TableColumn<Order, Integer> quantityColumn;

    @FXML private TableView<Order> ordersTv;
    @FXML private Button removeBtn;

    @FXML
    public void initialize() {

        productNameColumn.setCellValueFactory(
            cellData -> cellData.getValue().getProduct().nameProperty()
        );

        quantityColumn.setCellValueFactory(
            cellData -> cellData.getValue().quantityProperty().asObject()
        );

        ordersTv.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        ordersTv.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldOrder, newOrder) -> removeBtn.setDisable(newOrder == null)
        );

        ordersTv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ordersTv.setItems(model.getOrders());
    }

    @FXML
    public void remove(){
        // create a copy
        Order selectedOrder = ordersTv.getSelectionModel().getSelectedItem();
        model.removeOrder(selectedOrder);
        ObservableList<Product> catalogue = model.getCatalogue();
        catalogue.add(selectedOrder.getProduct());
    }

    @FXML
    public void close(){
        stage.close();
    }
}

