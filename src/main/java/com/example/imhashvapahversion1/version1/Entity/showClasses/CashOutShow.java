package com.example.imhashvapahversion1.version1.Entity.showClasses;

import java.util.Date;

public class CashOutShow {
    private Long id;
    private String outType;
    private Date outDate;
    private String outCash;

    public CashOutShow() {
    }

    public CashOutShow(Long id, String outType, Date outDate, String outCash) {
        this.id = id;
        this.outType = outType;
        this.outDate = outDate;
        this.outCash = outCash;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOutType() {
        return outType;
    }

    public void setOutType(String outType) {
        this.outType = outType;
    }

    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    public String getOutCash() {
        return outCash;
    }

    public void setOutCash(String outCash) {
        this.outCash = outCash;
    }
}
