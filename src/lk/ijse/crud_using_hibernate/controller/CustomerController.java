package lk.ijse.crud_using_hibernate.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
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
import lk.ijse.crud_using_hibernate.entity.Customer;
import lk.ijse.crud_using_hibernate.repository.CustomerRepository;
import lk.ijse.crud_using_hibernate.util.AlertController;

public class CustomerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSalary;

    @FXML
    private JFXButton btnHome;

    @FXML
    private AnchorPane ancPaneCustomer;

    @FXML
    private TableView<Customer> tViewCustomer;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        CustomerRepository customerRepository = new CustomerRepository();
        Customer customer = customerRepository.getCustomer(txtId.getText());
        customerRepository=new CustomerRepository();
        boolean deleted = customerRepository.deleteCustomer(customer);
        if (deleted) {
            AlertController.confirmmessage("Process Completed","Customer details deleted successfully");
            setDataToTableView();
        } else {
            AlertController.errormessage("Process Interrupted","Customer details deletion failed \n " +
                    "Please try again");
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        CustomerRepository customerRepository = new CustomerRepository();
        Customer customer = new Customer();
        customer.setId(txtId.getText());
        customer.setName(txtName.getText());
        customer.setAddress(txtAddress.getText());
        customer.setSalary(Double.parseDouble(txtSalary.getText()));
        String cust_id=customerRepository.saveCustomer(customer);
        if (cust_id.equals("failed")) {
            AlertController.errormessage("Process Terminated","Customer details saving unsuccessful \n " +
                    "Please resubmit the information  ");
        } else {
            AlertController.confirmmessage("Process Completed","Customer details saved successfully");
            setDataToTableView();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        CustomerRepository customerRepository = new CustomerRepository();
        Customer customer = new Customer();
        customer.setId(txtId.getText());
        customer.setName(txtName.getText());
        customer.setAddress(txtAddress.getText());
        customer.setSalary(Double.parseDouble(txtSalary.getText()));
        boolean updated=customerRepository.updateCustomer(customer);
        if (updated) {
            AlertController.confirmmessage("Process Completed","Customer details updated successfully");
            setDataToTableView();
        } else {
            AlertController.errormessage("Process Terminated","Customer details updating unsuccessfull. \n " +
                    "Please resubmit the information  ");
        }
    }

    @FXML
    void txtIdOnAction(ActionEvent event) {

    }


    @FXML
    void initialize() {
        assert btnDelete != null : "fx:id=\"btnDelete\" was not injected: check your FXML file 'customer.fxml'.";
        assert btnHome != null : "fx:id=\"btnHome\" was not injected: check your FXML file 'customer.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'customer.fxml'.";
        assert btnUpdate != null : "fx:id=\"btnUpdate\" was not injected: check your FXML file 'customer.fxml'.";
        assert txtAddress != null : "fx:id=\"txtAddress\" was not injected: check your FXML file 'customer.fxml'.";
        assert txtId != null : "fx:id=\"txtId\" was not injected: check your FXML file 'customer.fxml'.";
        assert txtName != null : "fx:id=\"txtName\" was not injected: check your FXML file 'customer.fxml'.";
        assert txtSalary != null : "fx:id=\"txtSalary\" was not injected: check your FXML file 'customer.fxml'.";

        Image image = new Image(getClass().getResourceAsStream("/lk/ijse/crud_using_hibernate/assets/images/dashboard.png"));

        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setPreserveRatio(true);

        btnHome.setGraphic(imageView);

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));

        setDataToTableView();
    }

    public void setDataToTableView(){
        ObservableList<Customer> customerList = new CustomerRepository().getDetailsToTableView();
        tViewCustomer.setItems(customerList);
    }

    public void btnHomeOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/lk/ijse/crud_using_hibernate/view/home.fxml"));
        ancPaneCustomer.getChildren().clear();
        ancPaneCustomer.getChildren().add(load);
    }

    public void tblOnMouseClicked(MouseEvent mouseEvent) {
        TablePosition pos = tViewCustomer.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        // Get the data from the selected row
        ObservableList<TableColumn<Customer, ?>> columns = tViewCustomer.getColumns();

        txtId.setText(columns.get(0).getCellData(row).toString());
        txtAddress.setText(columns.get(2).getCellData(row).toString());
        txtName.setText(columns.get(1).getCellData(row).toString());
        txtSalary.setText(columns.get(3).getCellData(row).toString());
    }
}
