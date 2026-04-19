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



    }

