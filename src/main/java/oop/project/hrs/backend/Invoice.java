package oop.project.hrs.backend;

import java.time.LocalDate;
import java.util.ArrayList;

    public class Invoice {

        private Reservation reservation;
        private double totalAmount;

        private ArrayList<PaymentMethod> paymentMethods;
        private ArrayList<Double> paymentAmounts;

        private double paidAmount;
        private LocalDate paymentDate;
        private boolean isPaid;

        public Invoice(Reservation reservation, double totalAmount) {

            if (reservation == null) {
                throw new IllegalArgumentException("Reservation cannot be null");
            }

            this.reservation = reservation;
            this.paymentMethods = new ArrayList<>();
            this.paymentAmounts = new ArrayList<>();
            this.paidAmount = 0;
            this.isPaid = false;

        }
        public void calculateTotal() {

            Rooms room = reservation.getRoom();

            double total = room.getBasePrice();

            for (Amenity a : room.getRoomAmenities()) {
                total += a.getPrice() * a.getCount();
            }

            this.totalAmount = total;
        }

        public void addPayment(double amount, PaymentMethod method) {

            if (isPaid) {
                throw new IllegalStateException("Invoice already fully paid");
            }

            if (amount <= 0) {
                throw new IllegalArgumentException("Invalid payment amount");
            }

            double remaining = totalAmount - paidAmount;

            if (amount > remaining) {
                throw new IllegalArgumentException("Overpayment not allowed");
            }

            //record payment
            paidAmount += amount;
            paymentMethods.add(method);
            paymentAmounts.add(amount);

            if (paymentDate == null) {
                paymentDate = LocalDate.now();
            }

            if (paidAmount == totalAmount) {
                isPaid = true;
                reservation.complete();
            }
        }
        //Getters

        public Reservation getReservation() {
            return reservation;
        }

        public double getTotalAmount() {
            return totalAmount;
        }

        public double getPaidAmount() {
            return paidAmount;
        }

        public boolean isPaid() {
            return isPaid;
        }

        public ArrayList<PaymentMethod> getPaymentMethods() {
            return paymentMethods;
        }
        public ArrayList<Double> getPaymentAmounts() {
            return paymentAmounts;
        }

        public LocalDate getPaymentDate() {
            return paymentDate;
        }
    }
