package oop.project.hrs.backend;
import java.time.LocalDate;
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
    public String listAvailableRooms() {
        return Database.displayAllRooms();
    }
}
