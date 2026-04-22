package oop.project.hrs.backend;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//Public class Guest extends User
public class Guest {
    private String username;
    private String password; //Can't be empty, 8 characters at least
    private LocalDate dateOfBirth;
    private double balance;
    private String address;
    private Gender gender;
    private String roomPreferences;
    //Waiting for roomPreferences to be implemented
    public Guest(String username, String password, Gender gender, LocalDate dateOfBirth, double balance, String address, String roomPreferences) {
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.balance = balance;
        this.address = address;
        this.roomPreferences = roomPreferences;
    }
    //Getter Setter methods
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getRoomPreferences() {
        return roomPreferences;
    }

    public void setRoomPreferences(String roomPreferences) {
        this.roomPreferences = roomPreferences;
    }
    public List listAvailableRooms() {
        return Database.displayAvailableRooms();
    }
    public boolean makeReservation(String username, int roomNum, LocalDate checkIn, LocalDate checkOut) {
        Rooms room = Database.getRoomByNum(roomNum);
        Guest guest = Database.getGuestByUsername(username);
        if (room != null && guest != null && room.getStatus() == Status.UNBOOKED) {
            Database.addReservation(new Reservation(guest, room, checkIn, checkOut));
            room.setStatus(Status.BOOKED);
            return true;
        }
        return false;
    }
    public ArrayList<Reservation> getUserReservations() {
        ArrayList<Reservation> userReservations = new ArrayList<>();
        ArrayList<Reservation> reservations = Database.getReservations();
        for (Reservation res : reservations) {
            if (res.getGuest().getUsername().equals(username)) {
                userReservations.add(res);
            }
        }
        return userReservations;
    }
    public void cancelReservations() {
        ArrayList<Reservation> reservations = Database.getReservations();
        for (Reservation res : reservations) {
            if (res.getGuest().getUsername().equals(username)) {
                Database.removeReservation(res);
            }
        }
    }
    public boolean checkout() {
        Reservation activeRes = null;
        for (Reservation res : getUserReservations()) {
            if (res.getStatus() == ReservationStatus.PENDING || res.getStatus() == ReservationStatus.CONFIRMED) {
                activeRes = res;
                break;
            }
        }
        if (activeRes == null) {
            return false; // No active stay to check out from
        }
        Invoice invoice = activeRes.getInvoice();
        double remaining = invoice.getTotalAmount() - invoice.getPaidAmount();
        // 2. Validate funds
        if (this.balance < remaining) {
            throw new ProjectExceptions.InsufficientBalanceException(this.balance);
        }
        // 3. Process payment
        this.balance -= remaining;
        invoice.addPayment(remaining, PaymentMethod.CASH);
        return true;
    }
}
