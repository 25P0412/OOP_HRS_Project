package oop.project.hrs.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import oop.project.hrs.backend.*;
import java.time.LocalDate;

public class ReservationController {
    @FXML private TableView<Reservation> reservationTable;
    @FXML private TableColumn<Reservation, Guest> guestCol;
    @FXML private TableColumn<Reservation, Rooms> roomCol;
    @FXML private TableColumn<Reservation, LocalDate> checkInCol;
    @FXML private TableColumn<Reservation, ReservationStatus> statusCol;
    @FXML private TextField guestNameField, roomNumField;
    @FXML private DatePicker checkInPicker, checkOutPicker;
    @FXML private Label statusLabel;
    private ObservableList<Reservation> masterData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        guestCol.setCellValueFactory(new PropertyValueFactory<>("guest"));
        roomCol.setCellValueFactory(new PropertyValueFactory<>("room"));
        checkInCol.setCellValueFactory(new PropertyValueFactory<>("checkIn"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        refreshTable();
    }

    private void refreshTable() {
        masterData.setAll(Database.getReservations());
        reservationTable.setItems(masterData);
    }
    @FXML
    private void handleConfirmBooking(ActionEvent event) {
        try {
            if (guestNameField.getText().isEmpty() || roomNumField.getText().isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Data Missing", "Please fill all fields!");
                return;
            }

            Guest guest = Database.getGuestByUsername(guestNameField.getText().trim());
            Rooms room = Database.getRoomByNum(Integer.parseInt(roomNumField.getText().trim()));

            if (guest == null) throw new Exception("Guest not found.");

            Reservation newRes = new Reservation(guest, room, checkInPicker.getValue(), checkOutPicker.getValue());
            newRes.confirm(); // Transitions status and creates Invoice

            statusLabel.setText("Booking confirmed for Room #" + room.getRoomNum());
            refreshTable();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Booking Failed", e.getMessage());
        }
    }
        private void showAlert(Alert.AlertType type, String title, String content) {
            Alert alert = new Alert(type);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(content);
            alert.showAndWait();
        }
    }
