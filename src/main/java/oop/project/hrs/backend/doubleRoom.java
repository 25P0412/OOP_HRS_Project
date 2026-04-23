package oop.project.hrs.backend;

import java.util.ArrayList;

public class doubleRoom extends Rooms{
    //Constructor
    public doubleRoom (int roomNum, Guest guest, ArrayList <Amenity> masterDoubleAmenities){
        super (RoomType.DOUBLE, Status.BOOKED, masterDoubleAmenities, roomNum, 2, 2, 5700.00, guest);
    }
}
