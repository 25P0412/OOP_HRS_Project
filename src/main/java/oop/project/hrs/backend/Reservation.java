package oop.project.hrs.backend;

    import java.time.LocalDate;

    public class Reservation {
        private Guest guest;
        private Rooms room;
        private LocalDate checkIn;
        private LocalDate checkOut;
        private ReservationStatus status;
        private Invoice invoice;

        public Reservation(Guest guest, Rooms room, LocalDate checkIn, LocalDate checkOut) {

            if (guest == null || room == null) {
                throw new ProjectExceptions.InvalidReservationDataException();
            }

            if (room.getGuest() != null) {
                throw new ProjectExceptions.RoomAlreadyBookedException();
            }

            if (checkIn.isAfter(checkOut)) {
                throw new ProjectExceptions.InvalidDateRangeException("Invalid date range");
            }
            this.guest = guest;
            this.room = room;
            this.checkIn = checkIn;
            this.checkOut = checkOut;
            this.status = ReservationStatus.PENDING;
            // lock room immediately
            this.invoice = new Invoice(this, calculateTotalPrice());
            room.setGuest(guest);
            room.setStatus(Status.BOOKED);
        }
        public double calculateTotalPrice() {

            Rooms room = this.room;

            double total = 0;

            // base price
            total += room.getBasePrice();

            // room amenities
            for (Amenity a : room.getRoomAmenities()) {
                total += a.getPrice() * a.getCount();
            }

            // hotel amenities
            for (Amenity a : Database.getHotelAmenities()) {
                total += a.getPrice() * a.getCount();
            }

            return total;
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

        public Invoice getInvoice() {
            return invoice;
        }

       // Update check-in date with validation
       public void setCheckIn(LocalDate date) {
           if (date == null || date.isAfter(checkOut)) {
               throw new ProjectExceptions.InvalidDateRangeException("Invalid check-in date");
           }
           this.checkIn = date;
       }

        // Updates check-out date with validation
        public void setCheckOut(LocalDate date) {
            if (date == null || date.isBefore(checkIn)) {
                throw new ProjectExceptions.InvalidDateRangeException("Invalid check-out date");
            }
            this.checkOut = date;
        }

        //Status control
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

