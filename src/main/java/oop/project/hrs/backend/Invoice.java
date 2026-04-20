package oop.project.hrs.backend;

import java.time.LocalDate;
import java.util.ArrayList;

    public class Invoice {
        private double totalAmount;
        private String paymentMethod;
        private LocalDate paymentDate;

        public Invoice(double totalAmount, String paymentMethod) {
            this.totalAmount = totalAmount;
            this.paymentMethod = paymentMethod;
            this.paymentDate = LocalDate.now();
        }
}
