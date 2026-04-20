package oop.project.hrs.backend;

import java.time.LocalDate;
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

    //allRooms' array definition
    private static ArrayList<Rooms> allRooms = new ArrayList<>();

    //HotelAmenities' array definition
    private static ArrayList <Amenity> hotelAmenities = new ArrayList <>();

    // Reservation ArrayList definition
    private static ArrayList<Reservation> reservations = new ArrayList<>();
    //Invoice ArrayList definition
    private static ArrayList<Invoice> invoices = new ArrayList<>();

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

    //allRooms array's methods
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
    //hotelAmenities array's methods
    //CREATE
    public static void addHotelAmenities (Amenity newAmenity){
        hotelAmenities.add (newAmenity);
    }
    //READ
    public static String displayHotelAmenities (){
        StringBuilder result = new StringBuilder();
        for (Amenity a : hotelAmenities){
            result.append("Amenity" + a.getType() + " is of price " + a.getPrice() + " and count " + a.getCount());
        }
        return result.toString();
    }
    //UPDATE the price of a Hotel Amenity
    public static void updatePriceOfHotelAmenity(String type, double newPrice){
        for (Amenity a : hotelAmenities){
            if (a.getType().equalsIgnoreCase(type)){
               a.setPrice(newPrice);
            }
        }
    }
    //UPDATE the count of a Hotel Amenity
    public static void updateCountOfHotelAmenity(String type, int newCount){
        for (Amenity a : hotelAmenities){
            if (a.getType().equalsIgnoreCase(type)){
                a.setCount(newCount);
            }
        }
    }
    //DELETE
    public static void deleteHotelAmenity (String type){
        hotelAmenities.removeIf (a -> a.getType().equalsIgnoreCase(type));
    }


    // Create a reservation
    public static void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }
    // Read all reservations
    public static ArrayList<Reservation> getReservations() {
        return reservations;
    }
    // Update check-in date
    public static void updateReservationCheckIn(Reservation reservation, LocalDate newCheckIn) {
        if (reservation != null && newCheckIn != null) {
            reservation.setCheckIn(newCheckIn);
        }
    }

    // Update check-out date
    public static void updateReservationCheckOut(Reservation reservation, LocalDate newCheckOut) {
        if (reservation != null && newCheckOut != null) {
            reservation.setCheckOut(newCheckOut);
        }
    }

    // Confirm a reservation
    public static void confirmReservation(Reservation reservation) {
        if (reservation != null) {
            reservation.confirm();
        }
    }

    // Delete a reservation
    public static void removeReservation(Reservation reservation) {
        reservations.remove(reservation);
    }

    // Add a new invoice
    public static void addInvoice(Invoice invoice) {
        invoices.add(invoice);
    }
    // Remove an existing invoice
    public static void removeInvoice(Invoice invoice) {
        invoices.remove(invoice);
    }
    // Return the list of all invoices
    public static ArrayList<Invoice> getInvoices() {
        return invoices;
    }
}