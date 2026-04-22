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
    public static class InvalidReservationDataException extends BaseException {
        public InvalidReservationDataException() {
            super("Guest and Room cannot be null!");
        }
    }

    public static class RoomAlreadyBookedException extends BaseException {
        public RoomAlreadyBookedException() {
            super("Room is already booked!");
        }
    }

    public static class InvalidDateRangeException extends BaseException {
        public InvalidDateRangeException(String message) {
            super(message);
        }
    }
    public static class InvalidInvoiceException extends BaseException {
        public InvalidInvoiceException() {
            super("Invoice must be associated with a valid reservation!");
        }
    }

    public static class InvoiceAlreadyPaidException extends BaseException {
        public InvoiceAlreadyPaidException() {
            super("Invoice is already fully paid!");
        }
    }

    public static class InvalidPaymentAmountException extends BaseException {
        public InvalidPaymentAmountException() {
            super("Payment amount must be greater than zero!");
        }
    }

    public static class OverpaymentException extends BaseException {
        public OverpaymentException() {
            super("Payment exceeds remaining balance!");
        }
    }

   
}
