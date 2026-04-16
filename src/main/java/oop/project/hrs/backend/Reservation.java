package main.java.oop.project.hrs.backend;

    import java.time.LocalDate;

    public class Reservation {
        private Guest guest;
        private Room room;
        private LocalDate checkIn;
        private LocalDate checkOut;
        private String status;

        public Reservation(Guest guest, Room room, LocalDate checkIn, LocalDate checkOut) {
            this.guest = guest;
            this.room = room;
            this.checkIn = checkIn;
            this.checkOut = checkOut;
            this.status = "Pending";
        }
// getters and setters
        public Guest getGuest() {
            return guest;
        }
        public void setGuest(Guest guest) {
            this.guest = guest;
        }

        public Room getRoom() {
            return room;
        }
        public void setRoom(Room room) {
            this.room = room;
        }

        public LocalDate getCheckIn() {
            return checkIn;
        }
        public void setCheckIn(LocalDate checkIn) {
            this.checkIn = checkIn;
        }

        public LocalDate getCheckOut() {
            return checkOut;
        }
        public void setCheckOut(LocalDate checkOut) {
            this.checkOut = checkOut;
        }

        public String getStatus() {
            return status;
        }
        public void setStatus(String status) {
            this.status = status;
        }
    }

