
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
            double payAmount = Double.parseDouble(amountField.getText());
            PaymentMethod method = paymentMethodCombo.getValue();

            if (method == null) {
                statusLabel.setText("Error: Select a payment method.");
                return;
            }

            // Apply payment to the invoice via backend logic
            currentInvoice.addPayment(payAmount, method);

            // Check if fully paid to update room status
            if (currentInvoice.getTotalAmount() <= 0) {
                Database.updateRoomStatus(currentReservation.getRoom().getRoomNum(), Status.UNBOOKED);
                statusLabel.setText("Payment Complete! Room " + currentReservation.getRoom().getRoomNum() + " is now free.");
            } else {
                statusLabel.setText(String.format("Partial payment accepted. Remaining: $%.2f", currentInvoice.getTotalAmount()));
                currentReservation.getGuest().setBalance(currentReservation.getGuest().getBalance() - currentInvoice.getTotalAmount());
            }

        } catch (NumberFormatException e) {
            statusLabel.setText("Error: Please enter a valid number.");
        }
    }
}
