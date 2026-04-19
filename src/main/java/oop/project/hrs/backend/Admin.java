package oop.project.hrs.backend;
import java.time.LocalDate;
import java.util.ArrayList;

public class Admin extends Staff {

        public Admin(String username, String password, Role role, LocalDate dateOfBirth, int workinghours) {
            super(role, username, password, dateOfBirth, workinghours);
        }

        public void addRoomType() {
            // Method to add new room
        }

        public void manageRooms() {
            // CRUD operations on rooms
        }

        public void manageAmenities() {
            // CRUD operations on hotel amenities
        }




    }

