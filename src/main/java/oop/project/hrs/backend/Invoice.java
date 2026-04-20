package oop.project.hrs.backend;

import java.time.LocalDate;
import java.util.ArrayList;

    public class Invoice {

        private Reservation reservation;
        private double totalAmount;

        private ArrayList<PaymentMethod> paymentMethods;
        private double paidAmount;
        private LocalDate paymentDate;
        private boolean isPaid;

        public Invoice(Reservation reservation, double totalAmount) {

            if (reservation == null) {
                throw new IllegalArgumentException("Reservation cannot be null");
            }

            if (totalAmount < 0) {
                throw new IllegalArgumentException("Amount cannot be negative");
            }

            this.reservation = reservation;
            // base price from room
            this.totalAmount = reservation.getRoom().getBasePrice();

            this.paymentMethods = new ArrayList<>();
            this.paidAmount = 0;
            this.isPaid = false;
        }
        public void addPayment(double amount, PaymentMethod method) {
            paidAmount += amount;
            paymentMethods.add(method);

            paymentDate = LocalDate.now();

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

        public ArrayList<PaymentMethod> getPaymentMethods() {
            return paymentMethods;
        }

        public LocalDate getPaymentDate() {
            return paymentDate;
        }
    }
