package com.example.imhashvapahversion1.version1.Entity.partners;

public class Debt {
    private String Customer;
    private String debt;
    private String prepayment;

    public Debt() {
    }

    public Debt(String customer, String debt, String prepayment) {
        Customer = customer;
        this.debt = debt;
        this.prepayment = prepayment;
    }

    public String getCustomer() {
        return Customer;
    }

    public void setCustomer(String customer) {
        Customer = customer;
    }

    public String getDebt() {
        return debt;
    }

    public void setDebt(String debt) {
        this.debt = debt;
    }

    public String getPrepayment() {
        return prepayment;
    }

    public void setPrepayment(String prepayment) {
        this.prepayment = prepayment;
    }
}
