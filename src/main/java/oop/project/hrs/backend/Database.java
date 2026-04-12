package main.java.oop.project.hrs.backend;

import java.util.ArrayList;

public class Database {

    private static ArrayList<Guest> guests = new ArrayList<>();
    private static ArrayList<String> usernames = new ArrayList<>(); //Use this one to check if a username is taken.
    //rest of arrays;

    public static ArrayList<Guest> getGuests() {
        return guests;
    }

    public static void setGuests(ArrayList<Guest> guests) {
        Database.guests = guests;
    }

    public static ArrayList<String> getUsernames() {
        return usernames;
    }

    public static void setUsernames(ArrayList<String> usernames) {
        Database.usernames = usernames;
    }
}