package oop.project.hrs.backend;

    import java.time.LocalDate;

    public class Reservation {
        private Guest guest;
        private Rooms room;
        private LocalDate checkIn;
        private LocalDate checkOut;
        private ReservationStatus status;

        public Reservation(Guest guest, Rooms room, LocalDate checkIn, LocalDate checkOut) {
            if (guest == null || room == null) {
                throw new IllegalArgumentException("Guest and Room cannot be null");
            }
            if (checkIn.isAfter(checkOut)) {
                throw new IllegalArgumentException("Invalid date range");
            }
            this.guest = guest;
            this.room = room;
            this.checkIn = checkIn;
            this.checkOut = checkOut;
            this.status = ReservationStatus.PENDING;
        }
// getters and setters
        public Guest getGuest() {

            return guest;
        }

        public Rooms getRoom() {
            return room;
        }

        public LocalDate getCheckIn() {

            return checkIn;
        }

        public LocalDate getCheckOut() {

            return checkOut;
        }

        public ReservationStatus getStatus() {

            return status;
        }
        public void setCheckIn(LocalDate date) {
            if (date == null || date.isAfter(checkOut)) {
                throw new IllegalArgumentException("Invalid check-in date");
            }
            this.checkIn = date;
        }

        public void setCheckOut(LocalDate date) {
            if (date == null || date.isBefore(checkIn)) {
                throw new IllegalArgumentException("Invalid check-out date");
            }
            this.checkOut = date;
        }

        // Status control
        public void confirm() {

            this.status = ReservationStatus.CONFIRMED;

        }

        public void cancel() {

            this.status = ReservationStatus.CANCELLED;

        }

        public void complete() {

            this.status = ReservationStatus.COMPLETED;

        }

    }

