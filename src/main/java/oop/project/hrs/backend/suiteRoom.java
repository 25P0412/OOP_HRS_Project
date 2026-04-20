package oop.project.hrs.backend;

import java.util.ArrayList;

public class suiteRoom extends Rooms {
    //Constructor
    public suiteRoom (int roomNum, int numOfGuests, Guest guest){
        //TODO: An error message to ensure that the number of guests does NOT exceed 5
        super ("Suite", defaultSuiteAmenities(), roomNum, numOfGuests, 5, 7500.00, guest);
    }

    //AMENITIES
    private static ArrayList<Amenity> defaultSuiteAmenities (){
        ArrayList <Amenity> list = new ArrayList <>();
        list.add(new Amenity ("Towel", 50.00, 5));
        list.add(new Amenity ("Mini-Bar", 75.00, 2));
        list.add(new Amenity ("AC", 90.00, 3));
        return list;
    }
}
