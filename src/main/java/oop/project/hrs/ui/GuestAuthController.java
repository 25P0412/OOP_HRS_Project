package oop.project.hrs.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import oop.project.hrs.backend.Database;
import oop.project.hrs.backend.Guest;
import oop.project.hrs.backend.GuestAuth;
import oop.project.hrs.backend.ProjectExceptions;

import java.io.IOException;
import java.util.Objects;

public class GuestAuthController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Text errorText;
    @FXML
    protected void onLoginButtonClick() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        try {
            Guest guest = GuestAuth.login(username, password);
            System.out.println(guest.getUsername()); //Switch these 2 lines later to make it switch to GuestDashboard
            System.out.println(Database.getUsernames());
        } catch (ProjectExceptions.InvalidLoginException e) {
            errorText.setText("Invalid username or password.");
            errorText.setVisible(true);
        }
    }
    public void switchToGuestRegister(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("GuestRegister.fxml")));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("GuestLogin.fxml")));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
