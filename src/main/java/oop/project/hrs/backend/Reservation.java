package oop.project.hrs.backend;

    import java.time.LocalDate;

    public class Reservation {
        private static int reservationIdcounter = 1;
        private int reservationId;
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

            if (checkIn.isAfter(checkOut)) {
                throw new ProjectExceptions.InvalidDateRangeException("Invalid date range");
            }
            for (Reservation r : Database.getReservations()) {
                if (r.getRoom().getRoomNum() == room.getRoomNum() &&
                        !(checkOut.isBefore(r.getCheckIn()) ||
                                checkIn.isAfter(r.getCheckOut()))) {

                    throw new ProjectExceptions.RoomAlreadyBookedException();
                }
            }

            this.reservationId = reservationIdcounter++;
            this.guest = guest;
            this.room = room;
            this.checkIn = checkIn;
            this.checkOut = checkOut;
            this.status = ReservationStatus.PENDING;
            Database.addReservation(this);
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
        public void refreshInvoice() {
            if (invoice != null && !invoice.isPaid()) {
                invoice.applyTotalUpdate(calculateTotalPrice());
                Database.updateInvoice(invoice);
            }
        }
// getters and setters

        public int getReservationId() {
            return reservationId;
        }

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

            if (status != ReservationStatus.PENDING) {
                throw new ProjectExceptions.InvalidReservationStateException(
                        "Only pending reservations can be confirmed"
                );
            }
            if (this.invoice != null) {
                throw new ProjectExceptions.InvalidReservationStateException(
                        "Invoice already exists"
                );
            }

            this.status = ReservationStatus.CONFIRMED;
            //lock room
            room.setGuest(guest);
            room.setStatus(Status.BOOKED);
            //invoice creation
            this.invoice = new Invoice(this);
            Database.addInvoice(this.invoice);
        }
        public void cancel() {

            this.status = ReservationStatus.CANCELLED;
            room.setGuest(null);
            room.setStatus(Status.UNBOOKED);// release room
        }

        public void complete() {

            this.status = ReservationStatus.COMPLETED;
            room.setGuest(null);
            room.setStatus(Status.UNBOOKED);// checkout releases room

        }

    }

