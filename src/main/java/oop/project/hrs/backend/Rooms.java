package oop.project.hrs.backend;

public abstract class Rooms {
    private int roomNum;
    private int numOfGuests;
    private int numOfBeds;
    private double price;
    private Guest guest;

    //Constructor
    public Rooms (int roomNum, int numOfGuests, int numOfBeds, double price, Guest guest){
        this.roomNum=roomNum;
        this.numOfGuests=numOfGuests;
        this.numOfBeds=numOfBeds;
        this.price=price;
        this.guest=guest;
    }

    //Getters and setters methods
    public int getRoomNum () {return roomNum;}
    public void setRoomNum (int roomNum) {this.roomNum=roomNum;}
    public int getNumOfGuests () {return numOfGuests;}
    public void setNumOfGuests (int numOfGuests) {this.numOfGuests=numOfGuests;}
    public int getNumOfBeds () {return numOfBeds;}
    public void setNumOfBeds (int numOfBeds) {this.numOfBeds=numOfBeds;}
    public double getPrice () {return price;}
    public void setPrice (double price) {this.price=price;}
    public Guest getGuest () {return guest;}
    public void setGuest (Guest guest) {this.guest=guest;}
}
