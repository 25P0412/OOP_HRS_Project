package oop.project.hrs.backend;

public class Amenity {
    private String type;
    private double price;
    private int count;

    //Constructor
    public Amenity (String type, double price, int count){
        this.type=type;
        this.price=price;
        this.count=count;
    }

    //Getters and setters methods
    public String getType () {return type;}
    public void setType (String type) {this.type=type;}
    public double getPrice (){return price;}
    public void setPrice (double price) {this.price=price;}
    public int getCount () {return count;};
    public void setCount (int count){this.count=count;}
}
