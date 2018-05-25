package com.example.imhashvapahversion1.version1.Entity.showClasses;

public class SupplierShow {
    private Long id;
    private String suplllierName;
    private String phoneNumber;
    private String address;
    private String hvhh;
    private Boolean full;
    private String type;

    public SupplierShow(Long id, String suplllierName, String phoneNumber, String address, String hvhh, Boolean full, String type) {
        this.id = id;
        this.suplllierName = suplllierName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.hvhh = hvhh;
        this.full = full;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSuplllierName() {
        return suplllierName;
    }

    public void setSuplllierName(String suplllierName) {
        this.suplllierName = suplllierName;
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

    public Boolean getFull() {
        return full;
    }

    public void setFull(Boolean full) {
        this.full = full;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
