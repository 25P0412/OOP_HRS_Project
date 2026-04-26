package oop.project.hrs.backend;

import java.util.ArrayList;

public class suiteRoom extends Rooms {
    //Constructor
    public suiteRoom (int roomNum, int numOfGuests, Guest guest, ArrayList <Amenity> masterSuiteAmenities){
        //TODO: An error message to ensure that the number of guests does NOT exceed 5
        super (RoomType.SUITE, Status.BOOKED, masterSuiteAmenities, roomNum, numOfGuests, 5, 7500.00, guest);
    }
}
