package com.example.imhashvapahversion1.version1.Entity.cash.use.classes;

import java.sql.Date;

public class Out {

    private String type;
    private Date inDate;
    private String inCash;
    private String note;

    public Out() {
    }


    public Out(String type, Date inDate, String inCash, String note) {
        this.type = type;
        this.inDate = inDate;
        this.inCash = inCash;
        this.note = note;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
