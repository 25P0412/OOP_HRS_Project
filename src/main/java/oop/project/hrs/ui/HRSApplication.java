package oop.project.hrs.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oop.project.hrs.backend.Database;

import java.io.IOException;

public class HRSApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        /* Loading the new custom login and register view */
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login & register.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hotel Reservation System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Database.prePopulateData();
        launch();
    }
}