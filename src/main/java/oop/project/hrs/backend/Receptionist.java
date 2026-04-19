package oop.project.hrs.backend;
import java.time.LocalDate;
public class Receptionist extends Staff {
    public Receptionist(String username, String password, Role role, LocalDate dateOfBirth, int workinghours) {
            super(role, username, password, dateOfBirth, workinghours);
        }

        public void manageCheckIn(Guest guest, String roomNumber) {

        }

        public void manageCheckOut(Guest guest) {

        }



    }

