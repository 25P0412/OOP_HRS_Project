package oop.project.hrs.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import oop.project.hrs.backend.Guest;
import oop.project.hrs.backend.Reservation;
import oop.project.hrs.backend.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class GuestDashboardController {
    @FXML private VBox dashboardHome;
    @FXML private Parent viewProfile;
    @FXML private GuestViewProfileController viewProfileController;
    @FXML private Label welcomeLabel;
    @FXML private Label balanceLabel;
    @FXML private Label statusLabel;
    @FXML private Label usernameLabel;
    @FXML private Label genderLabel;

    @FXML private TableView<Reservation> reservationsTable;
    @FXML private TableColumn<Reservation, String> colRoom;
    @FXML private TableColumn<Reservation, LocalDate> colCheckIn;
    @FXML private TableColumn<Reservation, LocalDate> colCheckOut;
    @FXML private TableColumn<Reservation, String> colStatus;

    private Guest currentGuest;

    @FXML
    public void initialize() {
        colRoom.setCellValueFactory(new PropertyValueFactory<>("roomReference"));
        colCheckIn.setCellValueFactory(new PropertyValueFactory<>("checkInDate"));
        colCheckOut.setCellValueFactory(new PropertyValueFactory<>("checkOutDate"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        statusLabel.setText("Dashboard Ready.");
    }

    public void setGuest(Guest guest) {
        this.currentGuest = guest;
        refreshUI();
    }

    private void refreshUI() {
        if (currentGuest != null) {
            welcomeLabel.setText("Welcome, " + currentGuest.getUsername() + "!");
            balanceLabel.setText(currentGuest.getBalance() + "$");
            usernameLabel.setText(currentGuest.getUsername());
            genderLabel.setText(String.valueOf(currentGuest.getGender()));
            ObservableList<Reservation> guestReservations = FXCollections.observableArrayList();
            for (Reservation res : Database.getReservations()) {
                if (res.getGuest().equals(currentGuest)) {
                    guestReservations.add(res);
                }
            }
            reservationsTable.setItems(guestReservations);
        }
    }


    @FXML
        void handleHome() {
        viewProfile.setVisible(false);
        dashboardHome.setVisible(true);
        statusLabel.setText("Returned Home");
    }
    @FXML
    private void handleViewProfile() {
        if (viewProfileController != null) {
            viewProfileController.setGuest(currentGuest);
            viewProfileController.setMainController(this); // Pass reference for the Back button
        }

        dashboardHome.setVisible(false);
        viewProfile.setVisible(true);

        statusLabel.setText("Viewing Profile");
    }

    @FXML
    private void handleBrowseRooms() {
        statusLabel.setText("Navigating to Room Browsing...");
        // Logic to switch to roomBrowsing.fxml goes here
    }

    @FXML
    private void handleLogout() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to logout?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                // Logic to return to login.fxml
            }
        });
    }
}