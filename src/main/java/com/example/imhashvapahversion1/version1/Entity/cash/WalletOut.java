package com.example.imhashvapahversion1.version1.Entity.cash;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
public class WalletOut {
    @Id
    @GeneratedValue
    private Long id;

    private String outType;

    @NotNull(message = "Հարկավոր է նշել Ելքի ամսաթիվը")
    private Date outDate;
    @NotEmpty(message ="Հարկավոր է նշել Ելքի գումարը")
    private String outCash;
    private String note;

    public WalletOut() {
    }

    public WalletOut(String outType, Date outDate, String outCash, String note) {
        this.outType = outType;
        this.outDate = outDate;
        this.outCash = outCash;
        this.note = note;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
