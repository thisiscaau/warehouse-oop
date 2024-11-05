package controller.Supplier;

import au.edu.uts.ap.javafx.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;
import model.Exceptions.*;
import java.util.logging.*;

public class SupplierListController extends Controller<Organisation>{

    @FXML
    private Button shopBtn;

    @FXML
    private ListView<Supplier> suppliersLv;

    private User loggedInUser;

    @FXML
    public void initialize(){
        loggedInUser = model.getLoggedInUser();

        if (loggedInUser instanceof Manager){
            suppliersLv.setItems(((Manager) loggedInUser).getSuppliers().getSuppliers());
        }
        else {
            suppliersLv.setItems(model.getSuppliers().getSuppliers());
        }

        suppliersLv.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        suppliersLv.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldSupplier, newSupplier) -> shopBtn.setDisable(newSupplier == null)
        );
    }

    @FXML
    public void shop(){
        Supplier selectedSupplier = suppliersLv.getSelectionModel().getSelectedItem();
        try {
            if (loggedInUser instanceof Manager){
                ViewLoader.showStage(selectedSupplier, "/view/Supplier/SupplierManagerView.fxml", "Supplier " + selectedSupplier.getRegion(), new FixedStage("/image/supplier_icon.png"));
            }
            else {
                ViewLoader.showStage(selectedSupplier, "/view/Supplier/SupplierCustomerView.fxml", "Supplier " + selectedSupplier.getRegion(), new FixedStage("/image/supplier_icon.png"));
            }
        } catch (Exception e){
            System.out.println(e);
            System.out.println("ngu");
        }
        stage.close();
    }

    @FXML
    public void close(){
        stage.close();
    }
    
}

