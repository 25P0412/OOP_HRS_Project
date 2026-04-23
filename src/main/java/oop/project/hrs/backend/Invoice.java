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
                throw new ProjectExceptions.InvalidInvoiceException();
            }

            this.reservation = reservation;
            this.totalAmount = totalAmount;
            this.paymentMethods = new ArrayList<>();
            this.paymentAmounts = new ArrayList<>();
            this.paidAmount = 0;
            this.isPaid = false;
            this.totalAmount = totalAmount;
        }


        // Add payment (supports multiple payments)
        public void addPayment(double amount, PaymentMethod method) {

            if (isPaid) {
                throw new ProjectExceptions.InvoiceAlreadyPaidException();
            }

            if (amount <= 0) {
                throw new ProjectExceptions.InvalidPaymentAmountException();
            }

            double remaining = totalAmount - paidAmount;

            if (amount > remaining) {
                throw new ProjectExceptions.OverpaymentException();
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
        //Setter for total amount
        public void setTotalAmount(double totalAmount) {
            this.totalAmount = totalAmount;
        }
    }
