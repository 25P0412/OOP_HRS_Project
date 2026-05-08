package oop.project.hrs.ui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import oop.project.hrs.backend.*;

public class ReservationPaymentController {

    // Navigation Layers
    @FXML private VBox reservationView;
    @FXML private GridPane paymentView;
    @FXML private TableView<Reservation> historyTable;
    @FXML private Label totalAmountLabel;
    @FXML private ComboBox<String> paymentMethodBox;

    @FXML
    public void initialize() {
        paymentMethodBox.getItems().addAll("CASH", "CREDIT_CARD", "ONLINE");
        reservationView.setVisible(true);
        paymentView.setVisible(false);
    }

    @FXML
    private void showPaymentScreen() {
        reservationView.setVisible(false);
        paymentView.setVisible(true);
    }

    @FXML
    private void showReservationScreen() {
        paymentView.setVisible(false);
        reservationView.setVisible(true);
    }
    @FXML
    private void handleCancelReservation() {
        Reservation selected = historyTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Database.cancelReservation(selected);
            showFeedback("Success", "Reservation cancelled successfully.");
        } else {
            showFeedback("Selection Error", "Please select a reservation to cancel.");
        }
    }

    @FXML
    private void handleConfirmPayment() {
        if (paymentMethodBox.getValue() == null) {
            showFeedback("Input Error", "Please select a payment method.");
            return;
        }
        showFeedback("Payment Confirmed", "Your payment has been processed.");
    }

    private void showFeedback(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
