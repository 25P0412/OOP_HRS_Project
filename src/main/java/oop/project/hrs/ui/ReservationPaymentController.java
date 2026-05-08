package oop.project.hrs.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import oop.project.hrs.backend.*;
import javafx.scene.control.cell.PropertyValueFactory;
public class ReservationPaymentController {

    // Navigation Layers (StackPane children)
    @FXML private VBox reservationView;
    @FXML private GridPane paymentView;
    // Reservation Components
    @FXML private TableView<Reservation> historyTable;
    // Payment Components
    @FXML private Label totalAmountLabel;
    @FXML private ComboBox<String> paymentMethodBox;

    @FXML
    public void initialize() {
        // Initialize payment methods
        paymentMethodBox.getItems().addAll("CASH", "CREDIT_CARD", "ONLINE");
        TableColumn<Reservation, String> roomCol = new TableColumn<>("Room");
        roomCol.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        historyTable.getColumns().add(roomCol);
        refreshTable();
        reservationView.setVisible(true);
        paymentView.setVisible(false);
    }
        private void refreshTable(){
            if (Database.getReservations() != null) {
                historyTable.setItems(FXCollections.observableArrayList(Database.getReservations()));
            }
        }
    //NAVIGATION LOGIC (StackPane Switching)
    @FXML
    private void showPaymentScreen() {
        Reservation selected = historyTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            totalAmountLabel.setText(String.format("%.2f", selected.calculateTotalPrice()));
            reservationView.setVisible(false);
            paymentView.setVisible(true);
        } else {
            showFeedback("Selection Error", "Please select a reservation to check out.");
        }
    }

    @FXML
    private void showReservationScreen() {
        paymentView.setVisible(false);
        reservationView.setVisible(true);
    }
    // BUSINESS LOGIC (Milestone 1 Integration)
    @FXML
    private void handleNewReservation() {
        showFeedback("Navigation", "Redirecting to Room Booking screen...");
    }
    @FXML
    private void handleCancelReservation() {
        Reservation selected = historyTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Database.cancelReservation(selected);
            refreshTable();
            showFeedback("Success", "Reservation cancelled successfully.");
        } else {
            showFeedback("Selection Error", "Please select a reservation to cancel.");
        }
    }

    @FXML
    private void handleConfirmPayment() {
        String selectedMethod = paymentMethodBox.getValue();
        if (selectedMethod == null) {
            showFeedback("Input Error", "Please select a payment method.");
            return;
        }
        try {
            PaymentMethod method = PaymentMethod.valueOf(selectedMethod);
            showFeedback("Payment Confirmed", "Processed via " + method);
            showReservationScreen();
        } catch (Exception e) {
            showFeedback("Error", "Invalid payment method selected.");
        }
    }
// Feedback messages
    private void showFeedback(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
