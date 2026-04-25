package oop.project.hrs.backend;
import java.time.LocalDate;
public class StaffAuth{
    public static Staff login(String username, String password) {
        for (Staff s : Database.allStaff) {
            if (s.getUsername().equals(username) && s.getPass().equals(password)) {
                return s;
            }
        }
        throw new ProjectExceptions.InvalidLoginException();
    }
}
