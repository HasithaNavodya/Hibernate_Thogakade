package lk.ijse.crud_using_hibernate.controller;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import lk.ijse.crud_using_hibernate.entity.Customer;
import lk.ijse.crud_using_hibernate.entity.Order;
import lk.ijse.crud_using_hibernate.repository.OrderRepository;
import lk.ijse.crud_using_hibernate.util.AlertController;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class OrderController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane ancPaneOrder;

    @FXML
    private Button btnDelete;

    @FXML
    private JFXButton btnHome;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private DatePicker dpOrderDate;

    @FXML
    private TextField txtCustId;

    @FXML
    private TextField txtOrderId;

    @FXML
    private TableView<Order> tViewOrder;

    @FXML
    private TableColumn<Order, String> colCustId;

    @FXML
    private TableColumn<?, ?> colOdate;

    @FXML
    private TableColumn<?, ?> colOid;

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        OrderRepository orderRepository = new OrderRepository();
        Order order = orderRepository.getOrder(txtOrderId.getText());
        orderRepository = new OrderRepository();
        boolean deleted = orderRepository.deleteOrder(order);
        if (deleted) {
            setDataToTableView();
            AlertController.confirmmessage("Process Completed", "Order details deleted successfully");
        } else {
            AlertController.errormessage("Process Terminated", "Order details deleting unsuccessful.\n" +
                    "Please try again");
        }
    }

    @FXML
    void btnHomeOnAction(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/lk/ijse/crud_using_hibernate/view/home.fxml"));
        ancPaneOrder.getChildren().clear();
        ancPaneOrder.getChildren().add(load);
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        OrderRepository orderRepository = new OrderRepository();
        Order order = new Order();
        order.setId(txtOrderId.getText());
        order.setDate(dpOrderDate.getValue());
        Customer customer = new Customer();
        customer.setId(txtCustId.getText());
        order.setCustomer(customer);
        boolean saved = orderRepository.saveOrder(order);
        if (saved) {
            setDataToTableView();
            AlertController.confirmmessage("Process Completed", "Order details saved successfully");
        } else {
            AlertController.errormessage("Process Terminated", "Order details saving unsuccessful.\n" +
                    "Please resubmit the information");
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        OrderRepository orderRepository = new OrderRepository();
        Order order = new Order();
        order.setId(txtOrderId.getText());
        order.setDate(dpOrderDate.getValue());
        Customer customer = new Customer();
        customer.setId(txtCustId.getText());
        order.setCustomer(customer);
        boolean updated = orderRepository.updateOrder(order);
        if (updated) {
            setDataToTableView();
            AlertController.confirmmessage("Process Completed", "Order details updated successfully");
        } else {
            AlertController.errormessage("Process Terminated", "Order details updating unsuccessful.\n" +
                    "Please resubmit the information");
        }
    }

    @FXML
    void initialize() {
        assert btnDelete != null : "fx:id=\"btnDelete\" was not injected: check your FXML file 'orders.fxml'.";
        assert btnHome != null : "fx:id=\"btnHome\" was not injected: check your FXML file 'orders.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'orders.fxml'.";
        assert btnUpdate != null : "fx:id=\"btnUpdate\" was not injected: check your FXML file 'orders.fxml'.";
        assert dpOrderDate != null : "fx:id=\"dpOrderDate\" was not injected: check your FXML file 'orders.fxml'.";
        assert txtCustId != null : "fx:id=\"txtCustId\" was not injected: check your FXML file 'orders.fxml'.";
        assert txtOrderId != null : "fx:id=\"txtOrderId\" was not injected: check your FXML file 'orders.fxml'.";

        Image image = new Image(getClass().getResourceAsStream("/lk/ijse/crud_using_hibernate/assets/images/dashboard.png"));

        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setPreserveRatio(true);

        btnHome.setGraphic(imageView);

        colOid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colOdate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colCustId.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Order, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Order, String> param) {
                Order order = param.getValue();

                String customerId = order.getCustomer().getId(); // Get the customer ID from the customer object

                return new SimpleStringProperty(customerId);
            }
        });

        setDataToTableView();
    }

    public void setDataToTableView() {
        ObservableList<Order> orderList = new OrderRepository().getDetailsToTableView();
        tViewOrder.setItems(orderList);
    }

    public void tblOnMouseClicked(MouseEvent mouseEvent) {
        TablePosition pos = tViewOrder.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        // Get the data from the selected row
        ObservableList<TableColumn<Order, ?>> columns = tViewOrder.getColumns();

        txtOrderId.setText(columns.get(0).getCellData(row).toString());
        dpOrderDate.setValue(LocalDate.parse(columns.get(1).getCellData(row).toString()));
        Customer customer= (Customer) columns.get(2).getCellData(row);
        txtCustId.setText(customer.getId());
    }
}
