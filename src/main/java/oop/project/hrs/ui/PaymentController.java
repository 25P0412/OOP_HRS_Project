
package oop.project.hrs.ui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import oop.project.hrs.backend.*;
import java.time.temporal.ChronoUnit;

public class PaymentController {

    @FXML private Label guestNameLabel;
    @FXML private Label roomNumLabel;
    @FXML private Label nightsStayedLabel;
    @FXML private Label totalPriceLabel;
    @FXML private Label statusLabel; // The bottom status feedback

    // Input fields
    @FXML private ComboBox<PaymentMethod> paymentMethodCombo;
    @FXML private TextField amountField;

    private Reservation currentReservation;
    private Invoice currentInvoice;

    @FXML
    public void initialize() {
        // Populate the combo box with Cash, Credit Card, etc.
        paymentMethodCombo.getItems().setAll(PaymentMethod.values());
    }

    // This method is called by the ReservationController during navigation
    public void loadReservationData(Reservation res) {
        this.currentReservation = res;
        this.currentInvoice = Database.getInvoiceByReservation(res);

        if (currentInvoice != null) {
            guestNameLabel.setText(res.getGuest().getUsername());
            roomNumLabel.setText(String.valueOf(res.getRoom().getRoomNum()));

            long nights = ChronoUnit.DAYS.between(res.getCheckIn(), res.getCheckOut());
            nightsStayedLabel.setText(String.valueOf(nights));

            totalPriceLabel.setText(String.format("$%.2f", currentInvoice.getTotalAmount()));
            statusLabel.setText("Invoice loaded for " + res.getGuest().getUsername());
        }
    }

    @FXML
    private void handleConfirmPayment() {
        try {
            // user input
            double payAmount = Double.parseDouble(amountField.getText());
            PaymentMethod method = paymentMethodCombo.getValue();

            // Validate input
            if (method == null) {
                statusLabel.setText("Error: Select a payment method.");
                // No specific error class in your CSS, so we use field-value
                statusLabel.getStyleClass().setAll("label", "field-value");
                return;
            }

            // Process payment in the Backend
            currentInvoice.addPayment(payAmount, method);

            // Update the Frontend labels
            double remaining = currentInvoice.getTotalAmount() - currentInvoice.getPaidAmount();
            totalPriceLabel.setText(String.format("$%.2f", remaining));
            totalPriceLabel.getStyleClass().setAll("label", "balance-highlight");

            // Check completion status
            if (currentInvoice.isPaid()) {
                // Update room status via Database
                Database.updateRoomStatus(currentReservation.getRoom().getRoomNum(), Status.UNBOOKED);

                statusLabel.setText("Payment Complete! Room " + currentReservation.getRoom().getRoomNum() + " is now free.");
                statusLabel.getStyleClass().setAll("label", "field-value");

                // Disable inputs to prevent further changes
                amountField.setDisable(true);
                paymentMethodCombo.setDisable(true);
            } else {
                statusLabel.setText(String.format("Accepted $%.2f. Balance: $%.2f", payAmount, remaining));
                statusLabel.getStyleClass().setAll("label", "field-value");
            }

        } catch (ProjectExceptions.OverpaymentException e) {
            statusLabel.setText("Error: Amount exceeds remaining balance.");
            statusLabel.getStyleClass().setAll("label", "field-value");
        } catch (NumberFormatException e) {
            statusLabel.setText("Error: Please enter a valid number.");
            statusLabel.getStyleClass().setAll("label", "field-value");
        }
    }
}
