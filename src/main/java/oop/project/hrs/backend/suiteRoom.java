package oop.project.hrs.backend;

public class suiteRoom extends Rooms {
    //Constructor
    public suiteRoom (int roomNum, int numOfGuests, Guest guest){
        //TODO: An error message to ensure that the number of guests does NOT exceed 5
        super ("Suite", roomNum, numOfGuests, 5, 7500.00, guest);
    }
}
