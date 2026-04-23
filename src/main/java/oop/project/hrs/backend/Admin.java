package oop.project.hrs.backend;
import java.time.LocalDate;
import java.util.ArrayList;

public class Admin extends Staff {

        public Admin(String username, String password, Role role, LocalDate dateOfBirth, int workinghours) {
            super(role, username, password, dateOfBirth, workinghours);
        }


    //  ROOMS CRUD
    public void addNewRoom(Rooms room) {
        Database.addRoom(room);
    }

    public void updateRoomNumber(int oldNum, int newNum) {
        Database.updateRoomNum(oldNum, newNum);
    }

    public void updateRoomType(int roomNum, String roomType) {
        Database.updateRoomType(roomNum, roomType);
    }

    public void deleteRoom(int roomNum) {
        Database.removeRoom(roomNum);
    }

    // AMENITIES CRUD
    public void addNewAmenity(Amenity amenity) {
        Database.addHotelAmenities(amenity);
    }

    public void viewAllAmenities() {
        System.out.println(Database.displayHotelAmenities());
    }

    public void updateAmenityPrice(String type, double price) {
        Database.updatePriceOfHotelAmenity(type, price);
    }

    public void updateAmenityCount(String type, int count) {
        Database.updateCountOfHotelAmenity(type, count);
    }

    public void deleteAmenity(String type) {
        Database.deleteHotelAmenity(type);
    }
    public void addAmenityToTypeTemplate(String roomType, Amenity amenity) {
        Database.addAmenityToType(roomType, amenity);
        System.out.println("New standard amenity added to " + roomType);
    }
    public void viewTypeTemplateAmenities(String roomType) {
        ArrayList<Amenity> list = Database.displayAllAmenitiesOfType(roomType);
        System.out.println("Amenities for " + roomType + ": " + list);
    }

    public void updateTypeTemplateAmenity(String roomType, String amenityName, double newPrice) {
        Database.updateAmenityOfType(roomType, amenityName, newPrice);
        System.out.println("Price updated for " + roomType + " standard amenities.");
    }

    public void removeAmenityFromTypeTemplate(String roomType, String amenityName) {
        Database.deleteAmenityFromType(roomType, amenityName);
        System.out.println("Amenity removed from " + roomType + " template.");
    }


    }

