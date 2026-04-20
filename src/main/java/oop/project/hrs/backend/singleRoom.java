package oop.project.hrs.backend;

import java.util.ArrayList;

public class singleRoom extends Rooms {
    //Constructor
    public singleRoom (int roomNum, Guest guest){
        super ("Single", defaultSingleAmenities(), roomNum, 1, 1, 3250.00, guest);
    }

    //AMENITIES
    private static ArrayList<Amenity> defaultSingleAmenities(){
        ArrayList<Amenity> list = new ArrayList<>();
        list.add(new Amenity ("Towel", 50.00, 1));
        list.add(new Amenity ("Mini-Bar", 75.00, 1));
        list.add(new Amenity("AC", 90.00, 1));
        return list;
    };

}
