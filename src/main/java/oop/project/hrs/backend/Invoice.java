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
