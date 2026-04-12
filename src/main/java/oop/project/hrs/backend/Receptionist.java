package main.java.oop.project.hrs.backend;
import java.time.LocalDate;
import javax.management.relation.Role;
import java.time.LocalDate;
public class Receptionist extends staff{
    public Receptionist(String username, String password, Role role, LocalDate dateOfBirth, int workinghours) {
            super(role, username, password, dateOfBirth, workinghours);
        }

        public void manageCheckIn(Guest guest, String roomNumber) {

        }

        public void manageCheckOut(Guest guest) {

        }



    }

