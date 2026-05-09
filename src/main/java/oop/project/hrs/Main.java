package oop.project.hrs;

import oop.project.hrs.backend.Database;
import oop.project.hrs.ui.DatabaseHelper;
import oop.project.hrs.ui.HRSApplication;

public class Main {
    public static void main(String[] args) {
        DatabaseHelper.initializeDatabase();
        DatabaseHelper.loadUsersFromDatabase();
        HRSApplication.main(args);
    }
}