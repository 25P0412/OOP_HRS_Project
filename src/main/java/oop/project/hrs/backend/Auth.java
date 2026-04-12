package main.java.oop.project.hrs.backend;

public class Auth {
    public static void register(Gender gender, Role role, String username, String password) {
        if (Database.usernames.contains(username)) { //Check if username is taken
            throw new ProjectExceptions.UsernameTakenException(username);
        }
        if (password.length() <= 8) {
            throw new ProjectExceptions.InvalidPasswordException();
        }
        Database.usernames.add(username);
        switch (role) { //Switch ArrayList based on role
            case GUST:
                Database.guests.add(new Guest(username, password, gender));
            //Other roles will be added
        }
    }
}
