
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
                statusLabel.getStyleClass().setAll("label", "field-value");
                return;
            }

            // Process payment in the Backend
            currentInvoice.addPayment(payAmount, method);

            // 3. Update the Frontend labels
            // Calculate the actual remaining balance
            double remaining = currentInvoice.getTotalAmount() - currentInvoice.getPaidAmount();

            // Update the price label with the new balance
            totalPriceLabel.setText(String.format("$%.2f", remaining));
            totalPriceLabel.getStyleClass().setAll("label", "balance-highlight");

            // Check completion status using the Invoice state
            if (currentInvoice.isPaid()) {
                // Update room status via Database
                Database.updateRoomStatus(currentReservation.getRoom().getRoomNum(), Status.UNBOOKED);

                statusLabel.setText("Payment Complete! Room " + currentReservation.getRoom().getRoomNum() + " is now free.");
                statusLabel.getStyleClass().setAll("label", "field-value");

                // Disable inputs to prevent further changes
                amountField.setDisable(true);
                paymentMethodCombo.setDisable(true);
            } else {
                // Success case: Partial payment
                statusLabel.setText(String.format("Partial payment accepted. Remaining: $%.2f", remaining));
                statusLabel.getStyleClass().setAll("label", "field-value");
            }

        } catch (ProjectExceptions.OverpaymentException e) {
            statusLabel.setText("Error: Amount exceeds remaining balance.");
            statusLabel.getStyleClass().setAll("label", "field-value");
        } catch (NumberFormatException e) {
            statusLabel.setText("Error: Please enter a valid number.");
            statusLabel.getStyleClass().setAll("label", "field-value");
        } catch (Exception e) {
            statusLabel.setText("Error: " + e.getMessage());
            statusLabel.getStyleClass().setAll("label", "field-value");
        }
    }
}
