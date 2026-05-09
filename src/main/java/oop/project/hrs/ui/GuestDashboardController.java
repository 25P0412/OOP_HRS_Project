package oop.project.hrs.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import oop.project.hrs.backend.Guest;
import oop.project.hrs.backend.Reservation;
import oop.project.hrs.backend.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.LocalDate;

public class GuestDashboardController {
    @FXML private VBox dashboardHome;
    @FXML private Parent viewProfile;
    @FXML private Parent payment;
    @FXML private Parent reservation;
    @FXML private Parent roomBrowsing;
    @FXML private GuestViewProfileController viewProfileController;
    @FXML private ReservationController reservationController;
    @FXML private RoomsController roomsController;
    @FXML private PaymentController paymentController;
    @FXML private Label welcomeLabel;
    @FXML private Label balanceLabel;
    @FXML private Label statusLabel;
    @FXML private Label usernameLabel;
    @FXML private Label genderLabel;
    @FXML private BorderPane mainContainer;

    @FXML private TableView<Reservation> reservationsTable;
    @FXML private TableColumn<Reservation, String> colRoom;
    @FXML private TableColumn<Reservation, LocalDate> colCheckIn;
    @FXML private TableColumn<Reservation, LocalDate> colCheckOut;
    @FXML private TableColumn<Reservation, String> colStatus;

    private Guest currentGuest;

    @FXML
    public void initialize() {
        colRoom.setCellValueFactory(new PropertyValueFactory<>("room"));
        colCheckIn.setCellValueFactory(new PropertyValueFactory<>("checkIn"));
        colCheckOut.setCellValueFactory(new PropertyValueFactory<>("checkOut"));
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
        roomBrowsing.setVisible(false);
        payment.setVisible(false);
        reservation.setVisible(false);
        dashboardHome.setVisible(true);
        statusLabel.setText("Returned to Dashboard!");
        refreshUI();
    }
    @FXML
    private void handleViewProfile() {
        if (viewProfileController != null) {
            viewProfileController.setGuest(currentGuest);
            viewProfileController.setMainController(this); // Pass reference for the Back button
        }
        viewProfile.setVisible(true);
        roomBrowsing.setVisible(false);
        payment.setVisible(false);
        reservation.setVisible(false);
        dashboardHome.setVisible(false);
        statusLabel.setText("Viewing Profile");
    }

    @FXML
    private void handleBrowseRooms() {
        statusLabel.setText("Navigating to Room Browsing...");
        viewProfile.setVisible(false);
        roomBrowsing.setVisible(true);
        payment.setVisible(false);
        reservation.setVisible(false);
        dashboardHome.setVisible(false);
        statusLabel.setText("Browsing Rooms!");
    }

    @FXML
    private void handleCheckout() {
        statusLabel.setText("Navigating to Checkout...");
        viewProfile.setVisible(false);
        roomBrowsing.setVisible(false);
        payment.setVisible(true);
        reservation.setVisible(false);
        dashboardHome.setVisible(false);
        statusLabel.setText("Checkout!");
    }
    @FXML
    private void handleReservations() {
        statusLabel.setText("Navigating to Reservations...");
        viewProfile.setVisible(false);
        roomBrowsing.setVisible(false);
        payment.setVisible(false);
        reservation.setVisible(true);
        dashboardHome.setVisible(false);
        statusLabel.setText("Viewing Reservations");
    }
    @FXML
    private void handleLogout() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to logout?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("login & register.fxml"));
                    Parent root = loader.load();
                    Stage stage = (Stage) mainContainer.getScene().getWindow();

                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.setTitle("Hotel Reservation System - Login");
                    stage.show();

                    System.out.println("User logged out successfully.");

                } catch (IOException e) {
                    System.out.println("Error: Could not return to Login screen.");
                    e.printStackTrace();
                }
            }
        });
    }
}