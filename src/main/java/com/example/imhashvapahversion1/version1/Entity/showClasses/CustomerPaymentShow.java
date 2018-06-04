package com.example.imhashvapahversion1.version1.Entity.showClasses;

import java.util.Date;

public class CustomerPaymentShow {
    private Long id;
    private Date paymentDate;
    private String paymentSum;
    private String customerName;
    private String type;

    public CustomerPaymentShow() {
    }

    public CustomerPaymentShow(Long id, Date paymentDate, String paymentSum, String customerName, String type) {
        this.id = id;
        this.paymentDate = paymentDate;
        this.paymentSum = paymentSum;
        this.customerName = customerName;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentSum() {
        return paymentSum;
    }

    public void setPaymentSum(String paymentSum) {
        this.paymentSum = paymentSum;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
