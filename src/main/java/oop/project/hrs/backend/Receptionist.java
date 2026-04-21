package oop.project.hrs.backend;
import java.time.LocalDate;
public class Receptionist extends Staff {
    public Receptionist(String username, String password, Role role, LocalDate dateOfBirth, int workinghours) {
            super(role, username, password, dateOfBirth, workinghours);
        }


     public void performCheckIn(Reservation res) {
            res.confirm();
            System.out.println("Check-in done for: " + res.getGuest().getName());
        }

        public void performCheckOut(Reservation res) {
            res.complete();
            System.out.println("Check-out done. Invoice: " + res.getRoom().getPrice() + " $");
        }

        public void cancelBooking(Reservation res) {
            res.cancel();
            System.out.println("Reservation cancelled.");
        }
    }

