package oop.project.hrs.backend;
import java.time.LocalDate;
import java.util.*;

//Public class Guest extends User
public class Guest {
    private String username;
    private String password; //Can't be empty, 8 characters at least
    private LocalDate dateOfBirth;
    private double balance;
    private String address;
    private Gender gender;
    private Set<RoomType> preferredTypes = new HashSet<>();
    //Waiting for roomPreferences to be implemented
    public Guest(String username, String password, Gender gender, LocalDate dateOfBirth, double balance, String address) {
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.balance = balance;
        this.address = address;
    }
    public void addPreference(RoomType type) {
        this.preferredTypes.add(type);
    }
    public void removePreferredType(RoomType type) {
        this.preferredTypes.remove(type);
    }
    public Set<RoomType> getPreferredTypes() {
        return Collections.unmodifiableSet(preferredTypes);
    }
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
    public List<Rooms> listRoomsByPreferences(Guest guest) {
        List<Rooms> allAvailable = Database.displayAvailableRooms();
        Set<RoomType> prefs = guest.getPreferredTypes();
        if (prefs.isEmpty()) {
            return allAvailable;
        }
        List<Rooms> filtered = new ArrayList<>();
        for (Rooms room : allAvailable) {
            if (prefs.contains(room.getRoomType())) {
                filtered.add(room);
            }
        }
        return filtered;
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
    public void cancelReservations(int id) {
        ArrayList<Reservation> reservations = Database.getReservations();
        for (Reservation res : reservations) {
            if (res.getReservationId() == id) {
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
            return false;
        }
        Invoice invoice = activeRes.getInvoice();
        double remaining = invoice.getTotalAmount() - invoice.getPaidAmount();
        // Validate funds
        if (this.balance < remaining) {
            throw new ProjectExceptions.InsufficientBalanceException(this.balance);
        }
        // Process payment
        this.balance -= remaining;
        invoice.addPayment(remaining, PaymentMethod.CASH);
        return true;
    }
}
