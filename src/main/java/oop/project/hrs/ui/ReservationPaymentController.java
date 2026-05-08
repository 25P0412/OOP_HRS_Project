package oop.project.hrs.ui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import oop.project.hrs.backend.*;

public class ReservationPaymentController {

    // Navigation Layers
    @FXML private VBox reservationView;
    @FXML private GridPane paymentView;

    @FXML
    public void initialize() {
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
}
