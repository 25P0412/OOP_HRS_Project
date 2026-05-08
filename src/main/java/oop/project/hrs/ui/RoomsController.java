package oop.project.hrs.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import oop.project.hrs.backend.Amenity;
import oop.project.hrs.backend.Rooms;

public class RoomsController {
    @FXML private ComboBox<String> roomTypeFilter;
    @FXML private TextField  roomPriceFilter;
    @FXML private TableView<Rooms> roomsTable;
    @FXML private TableColumn<Rooms, Integer> numberColumn;
    @FXML private TableColumn<Rooms, String> typeColumn;
    @FXML private TableColumn<Rooms, Double> priceColumn;
    @FXML private TableColumn<Rooms, String> amenitiesColumn;
    @FXML private CheckMenuItem towelCheck;
    @FXML private CheckMenuItem shampooCheck;
    @FXML private CheckMenuItem showerGelCheck;
    @FXML private CheckMenuItem slippersCheck;
    @FXML private CheckMenuItem electronicSafeCheck;

    private ObservableList<Rooms> masterData = FXCollections.observableArrayList();

    @FXML private void handleFilter (ActionEvent event) {

    }

    @FXML private void handleAmenityFilter (ActionEvent event) {

    }

    @FXML public void initialize() {
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("roomNum"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("basePrice"));


    }
}
