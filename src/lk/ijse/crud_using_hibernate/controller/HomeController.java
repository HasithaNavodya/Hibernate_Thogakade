package lk.ijse.crud_using_hibernate.controller;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

public class HomeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton btnCustomer;

    @FXML
    private JFXButton btnItems;

    @FXML
    private JFXButton btnOrders;

    @FXML
    private AnchorPane ancPaneHome;

    @FXML
    void btnCustomerOnAction(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/lk/ijse/crud_using_hibernate/view/customer.fxml"));
        ancPaneHome.getChildren().clear();
        ancPaneHome.getChildren().add(load);
    }

    @FXML
    void btnItemsOnAction(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/lk/ijse/crud_using_hibernate/view/item.fxml"));
        ancPaneHome.getChildren().clear();
        ancPaneHome.getChildren().add(load);
    }

    @FXML
    void btnOrdersOnAction(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/lk/ijse/crud_using_hibernate/view/orders.fxml"));
        ancPaneHome.getChildren().clear();
        ancPaneHome.getChildren().add(load);
    }

    @FXML
    void initialize() {
        assert btnCustomer != null : "fx:id=\"btnCustomer\" was not injected: check your FXML file 'home.fxml'.";
        assert btnItems != null : "fx:id=\"btnItems\" was not injected: check your FXML file 'home.fxml'.";
        assert btnOrders != null : "fx:id=\"btnOrders\" was not injected: check your FXML file 'home.fxml'.";

    }

}
