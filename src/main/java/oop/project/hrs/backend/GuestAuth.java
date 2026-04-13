package oop.project.hrs.backend;

import java.time.LocalDate;

public class GuestAuth {
    public static void register(String username, String password, Gender gender, LocalDate dateOfBirth, double balance, String address) {
        if (Database.usernameExists(username)) { //Check if username is taken
            throw new ProjectExceptions.UsernameTakenException(username);
        }
        if (password.length() <= 8) {
            throw new ProjectExceptions.InvalidPasswordException();
        }
        Database.addUsername(username);
        Database.addGuest(new Guest(username, password, gender, dateOfBirth, balance, address));
    }
    public static Guest login(String username, String password) { //find better way to implement it
        if (!Database.usernameExists(username) || !Database.getGuestByUsername(username).getPassword().equals(password)) {
            throw new ProjectExceptions.InvalidLoginException();
        }
        return Database.getGuestByUsername(username);
    }
}