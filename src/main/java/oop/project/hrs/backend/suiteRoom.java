package oop.project.hrs.backend;

import java.util.ArrayList;

public class suiteRoom extends Rooms {
    //Constructor
    public suiteRoom (int roomNum, int numOfGuests, Guest guest, ArrayList <Amenity> masterSuiteAmenities) throws ProjectExceptions.InvalidNumberOfGuestsException {
        super (RoomType.SUITE, Status.BOOKED, masterSuiteAmenities, roomNum, validate(numOfGuests), 5, 7500.00, guest);
    }
    //A HELPER METHOD to ensure that the number of guests is valid
    public static int validate (int numOfGuests){
        if (numOfGuests<3 || numOfGuests>5){
            throw new ProjectExceptions.InvalidNumberOfGuestsException();
        }
        return numOfGuests;
    }
}
