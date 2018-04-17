package com.example.imhashvapahversion1.version1.Entity.showClasses;

public class PartnerCustomerShow {
    private Long id;
    private String customerName;
    private String phoneNumber;
    private String address;
    private String hvhh;

    public PartnerCustomerShow(Long id, String customerName, String phoneNumber, String address, String hvhh) {
        this.id = id;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.hvhh = hvhh;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHvhh() {
        return hvhh;
    }

    public void setHvhh(String hvhh) {
        this.hvhh = hvhh;
    }
}
