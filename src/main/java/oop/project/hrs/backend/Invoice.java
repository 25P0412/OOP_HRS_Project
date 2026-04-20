package oop.project.hrs.backend;

import java.time.LocalDate;
import java.util.ArrayList;

    public class Invoice {

        private Reservation reservation;
        private double totalAmount;

        private ArrayList<PaymentMethod> paymentMethods;

        private LocalDate paymentDate;


        public Invoice(Reservation reservation, double totalAmount) {

            this.reservation = reservation;
            this.totalAmount = totalAmount;

            this.paymentMethods = new ArrayList<>();
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
