package oop.project.hrs.backend;

import java.util.ArrayList;

public class doubleRoom extends Rooms{
    //Constructor
    public doubleRoom (int roomNum, Guest guest){
        super ("Double", defaultDoubleAmenities(), roomNum, 2, 2, 5700.00, guest);
    }

    //AMENITIES
    private static ArrayList<Amenity> defaultDoubleAmenities(){
        ArrayList <Amenity> list = new ArrayList <>();
        list.add(new Amenity ("Towel", 50.00, 2));
        list.add(new Amenity ("Mini-Bar", 75.00, 1));
        list.add(new Amenity("AC", 90.00, 1));
        return list;
    }
}
