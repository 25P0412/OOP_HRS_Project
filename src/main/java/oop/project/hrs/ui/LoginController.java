package oop.project.hrs.ui;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import oop.project.hrs.backend.*;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;
public class LoginController {

    @FXML
    private TextField txt_username;
    @FXML
    private PasswordField txt_password;

    @FXML
    public void initialize() {
        if (Database.getGuestUsernames() != null) {
            System.out.println("Registered Guests (Usernames only):");
            Database.getGuestUsernames().forEach(name ->
                    System.out.println("- Guest: " + name)
            );
        } else {
            System.out.println("No guests found in system.");
        }


        if (Database.getStaff() != null) {
            System.out.println("Registered Staff (Full Info):");
            Database.getStaff().forEach(s ->
                    System.out.println("- Staff User: [" + s.getUsername() + "] | Pass: [" + s.getPass() + "]")
            );
        } else {
            System.out.println("No staff found in system.");
        }

    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void handleLogin() {
        String user = txt_username.getText().trim();
        String pass = txt_password.getText().trim();
        for (Staff s : Database.getStaff()) {
            if (s.getUsername().equals(user) && s.getPass().equals(pass)) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Welcome Staff: " + user);
                return;
            }
        }

        if (Database.getGuestUsernames().contains(user)) {
            try {
                Guest g = GuestAuth.login(user, pass);
                if (g != null) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Welcome Guest: " + user);
                    return;
                }
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Login Failed", "Wrong Password for Guest!");
                return;
            }
        }

        showAlert(Alert.AlertType.ERROR, "Login Failed", "User not found! Please Register first.");
    }

    @FXML
    private void goToRegister(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Hotel Registration");
            stage.show();
        } catch (Exception e) {
            System.out.println("Error: Could not load Register.fxml");
            e.printStackTrace();
        }
    }
}
