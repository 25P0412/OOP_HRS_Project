package oop.project.hrs.backend;

public class singleRoom extends Rooms {
    //Constructor
    public singleRoom (int roomNum, Guest guest){
        super ("Single", roomNum, 1, 1, 3250.00, guest);
    }
}
