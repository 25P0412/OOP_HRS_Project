package oop.project.hrs.backend;

public class Amenity {
    private String type;
    private double price;

    //Constructor
    public Amenity (String type, double price){
        this.type=type;
        this.price=price;
    }

    //Getters and setters methods
    public String getType () {return type;}
    public void setType (String type) {this.type=type;}
    public double getPrice (){return price;}
    public void setPrice (double price) {this.price=price;}
}
