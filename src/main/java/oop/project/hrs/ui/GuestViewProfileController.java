package oop.project.hrs.ui;

import javafx.scene.control.*;
import oop.project.hrs.backend.Guest;
import javafx.fxml.FXML;

public class GuestViewProfileController {

    @FXML private Label lblUsername;
    @FXML private Label lblDOB;
    @FXML private Label lblGender;
    @FXML private Label lblAddress;
    @FXML private Label lblBalance;
    @FXML private TextArea txtPreferences;
    @FXML private TextField txtEditAddress;
    @FXML private Button btnSave;
    @FXML private Button btnEdit;

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

//    @FXML
//    private void handleEditProfile() {
//        Alert info = new Alert(Alert.AlertType.INFORMATION);
//        info.setTitle("Edit Profile");
//        info.setHeaderText(null);
//        info.setContentText("Profile editing feature is under development.");
//        info.showAndWait();
//    }
    @FXML
    private void handleClose() {
        if (mainController != null) {
            mainController.handleHome();
        }
    }
    @FXML
    private void handleEditProfile() {
        // 1. Toggle visibility: Hide Labels, Show TextFields
        lblAddress.setVisible(false);
        txtEditAddress.setVisible(true);
        txtEditAddress.setText(currentGuest.getAddress());

        // 2. Toggle buttons
        btnEdit.setVisible(false);
        btnSave.setVisible(true);
    }

    @FXML
    private void handleSaveChanges() {
        if (currentGuest != null) {
            // 1. Update the backend model using setters
            currentGuest.setAddress(txtEditAddress.getText());

            // 2. Refresh the UI labels
            populateUI();

            // 3. Switch back to view mode
            lblAddress.setVisible(true);
            txtEditAddress.setVisible(false);
            btnEdit.setVisible(true);
            btnSave.setVisible(false);

            // 4. Provide feedback to the user
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Profile updated successfully!");
            alert.showAndWait();
        }
    }
}
