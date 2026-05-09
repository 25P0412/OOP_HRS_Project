package oop.project.hrs.ui;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import oop.project.hrs.backend.*;
import javafx.scene.input.MouseEvent;
import java.time.LocalDate;
public class RegisterController {
    @FXML private TextField txt_username;
    @FXML private PasswordField txt_password;
    @FXML private DatePicker reg_dob;
    @FXML private ComboBox<Gender> reg_gender;
    @FXML private TextField reg_address;
    @FXML private TextField reg_prefs;

    @FXML
    public void initialize() {
        if (reg_gender != null) {
            reg_gender.getItems().setAll(Gender.values());
        }
    }


    @FXML
    private void handleRegister(ActionEvent event) {
        try {
            if (txt_username.getText().isEmpty() || txt_password.getText().isEmpty() || reg_dob.getValue() == null) {
                showAlert(Alert.AlertType.WARNING, "Data Missing", "Please fill all required fields!");
                return;
            }

            String user = txt_username.getText().trim();
            String pass = txt_password.getText().trim();
            LocalDate dob = reg_dob.getValue();
            Gender gender = reg_gender.getValue();
            String address = reg_address.getText();
            double initialBalance = 0.0;

            String sql = "INSERT INTO guests(username, password, dob, gender, address, balance) VALUES(?,?,?,?,?,?)";

            try (java.sql.Connection conn = DatabaseHelper.connect();
                 java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, user);
                pstmt.setString(2, pass);
                pstmt.setString(3, dob.toString());
                pstmt.setString(4, gender.toString());
                pstmt.setString(5, address);
                pstmt.setDouble(6, initialBalance);

                pstmt.executeUpdate();
                System.out.println("User saved to SQLite successfully!");

            } catch (java.sql.SQLException e) {
                if (e.getMessage().contains("UNIQUE")) {
                    showAlert(Alert.AlertType.ERROR, "Error", "Username already exists!");
                    return;
                }
                throw e;
            }

            GuestAuth.register(user, pass, gender, dob, initialBalance, address);
            System.out.println("Guest registered in Auth system. User: " + user);

            showAlert(Alert.AlertType.INFORMATION, "Success", "Account created successfully!");

            Parent loginRoot = FXMLLoader.load(getClass().getResource("login & register.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(loginRoot));
            stage.setTitle("Hotel Login");
            stage.show();

        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Registration Failed", e.getMessage());
            e.printStackTrace();
        }
    }
    @FXML
    private void goToLogin(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
