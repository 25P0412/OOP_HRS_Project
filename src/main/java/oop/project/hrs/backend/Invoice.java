package oop.project.hrs.backend;

import java.time.LocalDateTime;

    public class Invoice {
        private double totalAmount;
        private String paymentMethod;
        private LocalDateTime paymentDate;

        public Invoice(double totalAmount, String paymentMethod) {
            this.totalAmount = totalAmount;
            this.paymentMethod = paymentMethod;
            this.paymentDate = LocalDateTime.now();
        }
}
