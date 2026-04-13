package oop.project.hrs.backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Database {

    private static ArrayList<Guest> guests = new ArrayList<>();
    private static ArrayList<String> usernames = new ArrayList<>(); //Use this one to check if a username is taken.
    //rest of arrays;
    public static void addGuest(Guest guest) {
        guests.add(guest);
    }
    public static void removeGuest(String username) {
        guests.remove(getGuestByUsername(username));
        usernames.remove(username);
    }
    public static void addUsername(String username) {
        usernames.add(username);
    }
    public static void removeUsername() {};
    public static boolean usernameExists(String search) {
        return usernames.contains(search);
    }
    public static ArrayList<String> getUsernames() {
        return usernames;
    }
    public static List<Guest> getGuests() {
        return Collections.unmodifiableList(guests);
    }
    public static Guest getGuestByUsername(String username) {
        for (Guest g: guests) {
            if (g.getUsername().equals(username)) {
                return g;
            }
        }
        return null;
    }
}