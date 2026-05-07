package oop.project.hrs.ui;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    private ComboBox<String> roleSelection;

    @FXML
    public void initialize() {
        roleSelection.getItems().addAll("Staff", "Guest");
        roleSelection.setValue("Guest");
    }

    @FXML
    private void handleLogin() {
        String username = txt_username.getText();
        String password = txt_password.getText();
        String selectedRole = roleSelection.getValue();

        if ("Staff".equals(selectedRole)) {
            Staff staff = StaffAuth.login(username, password);
            if (staff != null) {
                System.out.println("Success: Staff logged in");
            } else {
                System.out.println("Error: Staff not found");
            }
        } else {
            try {
                Guest guest = GuestAuth.login(username, password);
                if (guest != null) {
                    System.out.println("Success: Guest logged in");
                }
            } catch (Exception e) {
                System.out.println("Error: Guest login failed");
            }
        }
    }
}