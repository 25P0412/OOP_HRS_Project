package oop.project.hrs.backend;

import java.util.ArrayList;

public class singleRoom extends Rooms {
    //Constructor
    public singleRoom (int roomNum, Guest guest, ArrayList<Amenity> masterSingleAmenities){
        super ("Single", masterSingleAmenities, roomNum, 1, 1, 3250.00, guest);
    }
}