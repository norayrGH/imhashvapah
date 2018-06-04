package com.example.imhashvapahversion1.version1.Entity.showClasses;

public class CustomerDebtShow {
    private Long id;
    private String customerName;
    private Long customerDebt;
    private Long customerPrepayment;

    public CustomerDebtShow(Long id, String customerName, Long customerDebt, Long customerPrepayment) {
        this.id = id;
        this.customerName = customerName;
        this.customerDebt = customerDebt;
        this.customerPrepayment = customerPrepayment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Long getCustomerDebt() {
        return customerDebt;
    }

    public void setCustomerDebt(Long customerDebt) {
        this.customerDebt = customerDebt;
    }

    public Long getCustomerPrepayment() {
        return customerPrepayment;
    }

    public void setCustomerPrepayment(Long customerPrepayment) {
        this.customerPrepayment = customerPrepayment;
    }
}
