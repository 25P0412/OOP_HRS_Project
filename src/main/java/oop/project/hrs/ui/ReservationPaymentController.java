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
}
