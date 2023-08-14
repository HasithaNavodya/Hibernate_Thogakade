package lk.ijse.crud_using_hibernate;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        URL resource = Main.class.getResource("/lk/ijse/crud_using_hibernate/view/home.fxml");
        Parent load = FXMLLoader.load(resource);
        stage.setScene(new Scene(load));
        stage.setTitle("Dashboard");
        stage.getIcons().add(new Image("/lk/ijse/crud_using_hibernate/assets/images/dashboard.png"));
        stage.centerOnScreen();
        stage.show();
    }
}
