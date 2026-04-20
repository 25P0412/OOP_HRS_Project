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

            if (room.getGuest() != null) {
                throw new IllegalStateException("Room already booked");
            }
            if (checkIn.isAfter(checkOut)) {
                throw new IllegalArgumentException("Invalid date range");
            }
            this.guest = guest;
            this.room = room;
            this.checkIn = checkIn;
            this.checkOut = checkOut;
            this.status = ReservationStatus.PENDING;
            // lock room immediately
            room.setGuest(guest);
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

        // Status control
        public void confirm() {

            this.status = ReservationStatus.CONFIRMED;

        }

        public void cancel() {

            this.status = ReservationStatus.CANCELLED;
            room.setGuest(null); // release room
        }

        public void complete() {

            this.status = ReservationStatus.COMPLETED;
            room.setGuest(null); // checkout releases room

        }

    }

