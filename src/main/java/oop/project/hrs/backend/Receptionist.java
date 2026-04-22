package oop.project.hrs.backend;
import java.time.LocalDate;
public class Receptionist extends Staff {
    public Receptionist(String username, String password, Role role, LocalDate dateOfBirth, int workinghours) {
            super(role, username, password, dateOfBirth, workinghours);
        }

package oop.project.hrs.backend;

    public class Receptionist extends Staff {

        public Receptionist(String username, String password, Role role, java.time.LocalDate dateOfBirth, int workinghours) {
            super(role, username, password, dateOfBirth, workinghours);
        }

        public void performCheckIn(Reservation res) {
            if (res == null) throw new ProjectExceptions.InvalidReservationDataException();
            res.confirm();
            Database.updateRoomStatus(res.getRoom().getRoomNum(), Status.BOOKED);
            System.out.println("Check-in successful: " + res.getGuest().getUsername());
        }

        public void performCheckOut(Reservation res) {
            if (res == null) throw new ProjectExceptions.InvalidReservationDataException();
            Invoice inv = Database.getInvoiceByReservation(res);
            if (inv != null && !inv.isPaid()) {
                throw new ProjectExceptions.BaseException("Check-out failed: The invoice is not fully paid yet.");
            }
            res.complete();
            Database.updateRoomStatus(res.getRoom().getRoomNum(), Status.UNBOOKED);
            System.out.println("Check-out completed for Room " + res.getRoom().getRoomNum());
        }
        public void cancelBooking(Reservation res) {
            if (res == null) throw new ProjectExceptions.InvalidReservationDataException();
            res.cancel();
            Database.updateRoomStatus(res.getRoom().getRoomNum(), Status.UNBOOKED);
            System.out.println("Booking for Room " + res.getRoom().getRoomNum() + " cancelled.");
        }
    }



