package lk.ijse.crud_using_hibernate.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AlertController {
    public static void errormessage(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);

        alert.getDialogPane().setPrefSize(300, 150); // Set the size of the alert dialog pane
        alert.getDialogPane().setStyle("-fx-background-color: grey;"); // Set the background color of the alert dialog pane
        alert.getDialogPane().setHeaderText(null); // Remove the header text from the alert dialog pane

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("lk/ijse/crud_using_hibernate/assets/images/wrongicon.png"));
        ButtonType cancelButton = new ButtonType("Ok", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(cancelButton);

        alert.showAndWait();
    }

    public static void confirmmessage(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);

        alert.getDialogPane().setPrefSize(300, 150); // Set the size of the alert dialog pane
        alert.getDialogPane().setStyle("-fx-background-color: grey;"); // Set the background color of the alert dialog pane
        alert.getDialogPane().setHeaderText(null); // Remove the header text from the alert dialog pane

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("lk/ijse/crud_using_hibernate/assets/images/check.png"));
        ButtonType cancelButton = new ButtonType("Ok", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(cancelButton);

        alert.showAndWait();
    }


}
