package oop.project.hrs.backend;

import java.util.ArrayList;

public abstract class Rooms {
    //"Associated with one room type" requirement.
    private String roomType;

    //Each room is associated with its own amenities based on its type.
    private ArrayList<Amenity> roomAmenities;

    //Each room is associated with a status.
    private Status status;

    private int roomNum;
    private int numOfGuests;
    private int numOfBeds;
    private double basePrice;
    private Guest guest;

    //Constructor
    public Rooms (String roomType, Status status, ArrayList <Amenity> roomAmenities, int roomNum, int numOfGuests, int numOfBeds, double basePrice, Guest guest){
        this.roomType=roomType;
        this.status=status;
        this.roomAmenities=new ArrayList <>(roomAmenities);
        this.roomType=roomType;
        this.roomNum=roomNum;
        this.numOfGuests=numOfGuests;
        this.numOfBeds=numOfBeds;
        this.basePrice=basePrice;
        this.guest=guest;

    }

    //Getters and setters methods
    public String getRoomType () {return roomType;}
    public void setRoomType (String roomType) {this.roomType=roomType;}
    public int getRoomNum () {return roomNum;}
    public void setRoomNum (int roomNum) {this.roomNum=roomNum;}
    public int getNumOfGuests () {return numOfGuests;}
    public void setNumOfGuests (int numOfGuests) {this.numOfGuests=numOfGuests;}
    public int getNumOfBeds () {return numOfBeds;}
    public void setNumOfBeds (int numOfBeds) {this.numOfBeds=numOfBeds;}
    public double getBasePrice () {return basePrice;}
    public void setBasePrice(double basePrice) {this.basePrice=basePrice;}
    public Guest getGuest () {return guest;}
    public void setGuest (Guest guest) {this.guest=guest;}
    public Status getStatus () {return status;}
    public void setStatus (Status status) {this.status=status;}

    //Getter and setters methods for modifying an amenity in AN INTENDED ROOM
    //Getter (READ)
    public ArrayList<Amenity> getRoomAmenities(){return roomAmenities;}
    //Setters
    //CREATE (Add)
    public void addRoomAmenity (Amenity newAmenity){
        this.roomAmenities.add(newAmenity);
    }
    //UPDATE the price of a specific amenity
    public void updateRoomAmenity (String type, double newPrice){
        for (Amenity a : roomAmenities){
            if (a.getType().equalsIgnoreCase(type)){
                a.setPrice(newPrice);
                break;
            }
        }
    }
    //DELETE
    public void deleteRoomAmenity (String type){
        this.roomAmenities.removeIf(a -> a.getType().equalsIgnoreCase(type));
    }
}
