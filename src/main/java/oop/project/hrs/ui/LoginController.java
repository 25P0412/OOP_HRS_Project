package oop.project.hrs.ui;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import oop.project.hrs.backend.StaffAuth;
import oop.project.hrs.backend.GuestAuth;
import oop.project.hrs.backend.Staff;
import oop.project.hrs.backend.Guest;

    //handles the user interaction for login and directs authentication to Staff or Guest logic
    public class LoginController {

        @FXML
        private TextField txt_username;

        @FXML
        private PasswordField txt_password;

        @FXML
        private Label errorLabel;

        @FXML
        private void handleLogin() {
            String username = txt_username.getText();
            String password = txt_password.getText();
            Staff staff = StaffAuth.login(username, password);

            if (staff != null) {
                System.out.println("Login Successful as Staff: " + staff.getRole());
                // Navigate to Staff Dashboard
                return;
            }

            // 2. If not staff, try to login as a Guest
            try {
                Guest guest = GuestAuth.login(username, password);
                if (guest != null) {
                    System.out.println("Login Successful as Guest: " + guest.getUsername());
                    // Navigate to Guest Booking Page
                }
            } catch (Exception e) {
                // 3. If both failed, show error message
                System.out.println("Login failed: Invalid credentials");
                if (errorLabel != null) {
                    errorLabel.setText("Invalid Username or Password");
                }
            }
        }

        @FXML
        private void handleRegister() {
            // This handles navigation to the Guest Registration page
            System.out.println("Redirecting to Guest Registration...");
        }
    }

