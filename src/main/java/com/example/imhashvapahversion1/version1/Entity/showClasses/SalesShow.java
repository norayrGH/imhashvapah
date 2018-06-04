package com.example.imhashvapahversion1.version1.Entity.showClasses;

import java.util.Date;

public class SalesShow {
    private Long id;
    private String  saleNumber;
    private Date saleDate;
    private String sum;
    private String customerName;

    public SalesShow() {
    }

    public SalesShow(Long id, String saleNumber, Date saleDate, String sum, String customerName) {
        this.id = id;
        this.saleNumber = saleNumber;
        this.saleDate = saleDate;
        this.sum = sum;
        this.customerName = customerName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSaleNumber() {
        return saleNumber;
    }

    public void setSaleNumber(String saleNumber) {
        this.saleNumber = saleNumber;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
