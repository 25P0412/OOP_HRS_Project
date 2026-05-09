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

    @FXML
    private void handleClose() {
        if (mainController != null) {
            mainController.handleHome();
        }
    }
    @FXML
    private void handleEditProfile() {
        lblAddress.setVisible(false);
        txtEditAddress.setVisible(true);
        txtEditAddress.setText(currentGuest.getAddress());

        btnEdit.setVisible(false);
        btnSave.setVisible(true);
    }

    @FXML
    private void handleSaveChanges() {
        if (currentGuest != null) {
            currentGuest.setAddress(txtEditAddress.getText());
            populateUI();

            lblAddress.setVisible(true);
            txtEditAddress.setVisible(false);
            btnEdit.setVisible(true);
            btnSave.setVisible(false);

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Profile updated successfully!");
            alert.showAndWait();
        }
    }
}
