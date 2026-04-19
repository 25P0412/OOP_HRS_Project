package oop.project.hrs.backend;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

//Rewrite using hashmaps
public class Database {
    //Guests and usernames arrays' definition
//    private static ArrayList<Guest> guests = new ArrayList<>();
    private static HashMap<String, Guest> guests = new HashMap<>();
    private static ArrayList<String> usernames = new ArrayList<>(); //Use this one to check if a username is taken.

    //All rooms' array definition
    private static ArrayList<Rooms> allRooms = new ArrayList<>();

    //Guests and usernames arrays' methods
    public static void addGuest(String username, Guest guest) {
        guests.putIfAbsent(username, guest);
    }
    public static void removeGuest(String username) {
        guests.remove(username);
        usernames.remove(username);
    }
    public static void addUsername(String username) {
        usernames.add(username);
    }
    public static void removeUsername(String username) {
        usernames.remove(username);
    };
    public static boolean usernameExists(String search) {
        return usernames.contains(search);
    }
    public static List<String> getUsernames() {
        return Collections.unmodifiableList(usernames);
    }
   public static Set<String> getGuestUsernames() {
        return Collections.unmodifiableSet(guests.keySet());
    }
    public static Guest getGuestByUsername(String username) {
        return guests.get(username);
    }

    //All rooms array's methods
    //CREATE
    public static void addRoom (Rooms newRoom){
        allRooms.add(newRoom);
    }
    //READ
    public static String displayAllRooms (){
        StringBuilder result = new StringBuilder();
        for (Rooms r : allRooms){
            result.append("Room #" + r.getRoomNum() + " of type " + r.getRoomType() + "\n");
        }
        return result.toString();
    }
    //UPDATE room number
    public static void updateRoomNum (int oldNum, int newNum){
        for (Rooms r : allRooms){
            if (r.getRoomNum() == oldNum){
                r.setRoomNum(newNum);
            }
        }
    }
    //UPDATE room type
    public static void updateRoomType (int roomNum, String roomType){
        for (Rooms r : allRooms){
            if (r.getRoomNum() == roomNum){
                r.setRoomType(roomType);
            }
        }
    }
    //DELETE
    public static void removeRoom (int roomNum){
        allRooms.removeIf (r -> r.getRoomNum()== roomNum);
    }
}