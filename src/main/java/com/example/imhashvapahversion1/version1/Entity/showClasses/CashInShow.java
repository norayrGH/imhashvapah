package com.example.imhashvapahversion1.version1.Entity.showClasses;

import java.util.Date;

public class CashInShow {
    private Long id;
    private String inType;
    private Date inDate ;
    private String inCash;

    public CashInShow() {
    }

    public CashInShow(Long id, String inType, Date inDate, String inCash) {

        this.id = id;
        this.inType = inType;
        this.inDate = inDate;
        this.inCash = inCash;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInType() {
        return inType;
    }

    public void setInType(String inType) {
        this.inType = inType;
    }

    public Date getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public String getInCash() {
        return inCash;
    }

    public void setInCash(String inCash) {
        this.inCash = inCash;
    }
}
