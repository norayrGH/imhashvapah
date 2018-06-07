package com.example.imhashvapahversion1.version1.Entity.showClasses;

import java.util.Date;

public class PurchaseShow {
    String number;
    Date purchaseDate;
    String purchaseCost;
    String supplierName;
    String purchaseType;
    Long id;

    public PurchaseShow(String number, Date purchaseDate, String purchaseCost, String supplierName, String purchaseType, Long id) {
        this.number = number;
        this.purchaseDate = purchaseDate;
        this.purchaseCost = purchaseCost;
        this.supplierName = supplierName;
        this.purchaseType = purchaseType;
        this.id = id;
    }

    public PurchaseShow() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getPurchaseCost() {
        return purchaseCost;
    }

    public void setPurchaseCost(String purchaseCost) {
        this.purchaseCost = purchaseCost;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(String purchaseType) {
        this.purchaseType = purchaseType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
