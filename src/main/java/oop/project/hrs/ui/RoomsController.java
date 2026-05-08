package oop.project.hrs.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import oop.project.hrs.backend.Rooms;

import java.util.ArrayList;

public class RoomsController {
    @FXML private ComboBox<String> roomTypeFilter;
    @FXML private TextField  roomPriceFilter;
    @FXML private TableView<Rooms> roomsTable;
    @FXML private TableColumn<Rooms, Integer> numberColumn;
    @FXML private TableColumn<Rooms, String> typeColumn;
    @FXML private TableColumn<Rooms, Double> priceColumn;

    private ObservableList<Rooms> masterData = FXCollections.observableArrayList();

    @FXML private void handleFilter (ActionEvent event) {
        String selectedType = roomTypeFilter.getValue();
        String priceInput = roomPriceFilter.getText();

        ObservableList<Rooms> filteredList = FXCollections.observableArrayList();

        for (Rooms room : masterData) {
            boolean typeMatch = (selectedType == null || selectedType.equals("All") || room.getRoomType().name().equalsIgnoreCase(selectedType));
            boolean priceMatch = true;
            if (priceInput != null && !priceInput.trim().isEmpty()) {
                try {
                    double maxPrice = Double.parseDouble(priceInput);
                    priceMatch = room.getBasePrice() <= maxPrice;
                } catch (NumberFormatException e) {
                    priceMatch = true; // Skip filtering if input isn't a number
                }
            }
            if (typeMatch && priceMatch) {
                filteredList.add(room);
            }
        }
        roomsTable.setItems(filteredList);
    }

    @FXML public void initialize() {
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("roomNum"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("basePrice"));

        roomTypeFilter.getItems().addAll("All", "Single", "Double", "Suite");
        roomTypeFilter.getSelectionModel().select("All");

        roomsTable.setItems(masterData);
    }
    public void setRoomsData(ArrayList<Rooms> roomsList) {
        masterData.setAll(roomsList);
    }
}
