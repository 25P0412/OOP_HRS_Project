package oop.project.hrs.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HRSApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GuestLogin.fxml")); //Change this one later to homepage.fxml

        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Hotel Reservation System - Login"); //Change this one too later
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}