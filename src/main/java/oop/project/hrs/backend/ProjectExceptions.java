package oop.project.hrs.backend;
public class ProjectExceptions {
    public static class BaseException extends RuntimeException {
        public BaseException(String message) {
            super(message);
        }
    }
    public static class UsernameTakenException extends BaseException {
        public UsernameTakenException(String username) {
            super("The username '" + username + "' is already in use!");
        }
    }
    public static class InvalidPasswordException extends BaseException {
        public InvalidPasswordException() {
            super("Entered password doesn't meet requirements!");
        }
    }
    public static class InvalidLoginException extends BaseException {
        public InvalidLoginException() {
            super("Username or Password is invalid!");
        }
    }
}
