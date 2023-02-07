package com.example.crmapp.classes;

public class Expense {

        private String ID;
        private String expenseDate;
        private String amount;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getExpenseDate() {
            return expenseDate;
        }

        public void setExpenseDate(String expenseDate) {
            this.expenseDate = expenseDate;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }
}
