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
}