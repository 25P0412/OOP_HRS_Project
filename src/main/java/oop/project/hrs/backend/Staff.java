package oop.project.hrs.backend;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Staff {
    private Role role;
    private String username;
    private String password; // FIX: Changed data type to string, changed var name to password for better integration
    private LocalDate dateofbirth;
    private int workinghours;
    public Staff(Role role, String username, String password, LocalDate dateofbirth, int workinghours) {
        this.role = role;
        this.username = username;
        this.password = password;
        this.dateofbirth = dateofbirth;
        this.workinghours = workinghours;
    }
    public String getUsername() {
        return username;
    }
    public Role getRole() {
        return role;
    }
    public String getPass() {
        return password;
    }
    public LocalDate getDateofbirth() {
        return dateofbirth;
    }
    public int getWorkinghours() {
        return workinghours;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public void setUsername(String username) {
        if (Database.usernameExists(username)) { //Changed to public method
            throw new ProjectExceptions.UsernameTakenException(username);
        }
        this.username = username;
    }

    public void setPass(String password) {
        if (password == null || password.length() < 8) {
            throw new ProjectExceptions.InvalidPasswordException();
        }
        this.password = password;
    }

    public void setWorkinghours(int workinghours) {
        if (workinghours < 0) {
            throw new IllegalArgumentException("Working hours cannot be negative!");
        }
        this.workinghours = workinghours;
    }
    public void setDateofbirth(LocalDate dateofbirth) {
        this.dateofbirth = dateofbirth;
    }
    public void viewAllGuests() {
        Set<String> allUsernames = Database.getGuestUsernames();

        System.out.println("    Total Guests List    ");
        for (String name : allUsernames) {
            Guest guestInfo = Database.getGuestByUsername(name);
            if (guestInfo != null) {
                System.out.println("Username: " + guestInfo.getUsername());
                System.out.println("Balance: " + guestInfo.getBalance());
                System.out.println("Address: " + guestInfo.getAddress());
                System.out.println("Gender: " + guestInfo.getGender());
                System.out.println("                     ");
            }
        }
    }

    public void viewAllReservations() {
            ArrayList<Reservation> allReservations = Database.getReservations();
            if (allReservations.isEmpty()) {
                System.out.println("No reservations found.");
            }
            else {
                System.out.println("    List of Current Reservations   ");
                for (Reservation res : allReservations) {
                    System.out.println(res);
                }
            }
        }

    public void viewallrooms() {
// Method to display ALL rooms currently in the database

            System.out.println(" All Hotel Rooms ");
            for (Rooms r : Database.allRooms) {
                System.out.println("Room #" + r.getRoomNum() +
                        " | Type: " + r.getRoomType() +
                        " | Price: " + r.getBasePrice());

        }
    }
}
