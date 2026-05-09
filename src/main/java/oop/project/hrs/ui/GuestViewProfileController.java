package oop.project.hrs.ui;

import oop.project.hrs.backend.Guest;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert;

public class GuestViewProfileController {

    @FXML private Label lblUsername;
    @FXML private Label lblDOB;
    @FXML private Label lblGender;
    @FXML private Label lblAddress;
    @FXML private Label lblBalance;
    @FXML private TextArea txtPreferences;

    private Guest currentGuest;

    private GuestDashboardController mainController;
    public void setMainController(GuestDashboardController mainController) {
        this.mainController = mainController;
    }
    public void setGuest(Guest guest) {
        this.currentGuest = guest;
        populateUI();
    }

    private void populateUI() {
        if (currentGuest != null) {
            lblUsername.setText(currentGuest.getUsername());
            if (currentGuest.getDateOfBirth() != null) {
                lblDOB.setText(currentGuest.getDateOfBirth().toString());
            }
            lblGender.setText(currentGuest.getGender().name());
            lblAddress.setText(currentGuest.getAddress());
            lblBalance.setText(currentGuest.getBalance() + "$");
            txtPreferences.setText(currentGuest.getPreferredTypes().toString());
        }
    }

    @FXML
    private void handleEditProfile() {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("Edit Profile");
        info.setHeaderText(null);
        info.setContentText("Profile editing feature is under development.");
        info.showAndWait();
    }
    @FXML
    private void handleClose() {
        if (mainController != null) {
            mainController.handleHome();
        }
    }
}
