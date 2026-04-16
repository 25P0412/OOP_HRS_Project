package oop.project.hrs.backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Database {
    //Guests and usernames arrays' definition
    private static ArrayList<Guest> guests = new ArrayList<>();
    private static ArrayList<String> usernames = new ArrayList<>(); //Use this one to check if a username is taken.

    //All rooms' array definition
    private static ArrayList<Rooms> allRooms = new ArrayList<>();

    //Guests and usernames arrays' methods
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

    //All rooms array's methods
    //CREATE
    public void addRoom (Rooms newRoom){
        allRooms.add(newRoom);
    }
    //READ
    public void displayAllRooms (){
        for (Rooms r : allRooms){
            System.out.println ("Room #" + r.getRoomNum() + "of type" + r.getRoomType());
        }
    }
    //UPDATE room number
    public void updateRoomNum (int oldNum, int newNum){
        for (Rooms r : allRooms){
            if (r.getRoomNum() == oldNum){
                r.setRoomNum(newNum);
            }
        }
    }
    //UPDATE room type
    public void updateRoomType (int roomNum, String roomType){
        for (Rooms r : allRooms){
            if (r.getRoomNum() == roomNum){
                r.setRoomType(roomType);
            }
        }
    }
    //DELETE
    public void removeRoom (int roomNum){
        allRooms.removeIf (r -> r.getRoomNum()== roomNum);
    }

}