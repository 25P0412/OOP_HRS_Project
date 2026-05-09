package oop.project.hrs.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oop.project.hrs.backend.Database;
import oop.project.hrs.backend.Rooms;

import java.io.IOException;
import java.util.ArrayList;

public class HRSApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        /* Loading the new custom login and register view */
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login & register.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hotel Reservation System");
        stage.setScene(scene);
        stage.show();

        //ROOM BROWSING PART
        try {
            // 1. Load your FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/oop/project/hrs/ui/RoomBrowsing.fxml"));
            Parent root = loader.load();

            // 2. Access your Controller
            RoomsController controller = loader.getController();

            // 3. Prepare the data using the team's shared Database class
            Database.prePopulateData(); // Fills the internal data

            // 4. Pass the data to your controller
            // Note: Ensure Database.getRooms() exists and returns an ArrayList<Rooms>
            controller.setRoomsData(Database.getRooms());

            // 5. Display the scene (Teammate will likely handle the Stage part)
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: Could not find the RoomBrowsing.fxml file.");
        }
    }

    public static void main(String[] args) {
        Database.prePopulateData();
        launch();
    }
}