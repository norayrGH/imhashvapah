package com.example.imhashvapahversion1.version1.Entity.cash;

import com.example.imhashvapahversion1.version1.Entity.Organization;
import com.example.imhashvapahversion1.version1.Entity.cash.waletintypes.*;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;

import javax.persistence.Entity;

import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
public class WalletIn {
    @Id
    private Long id;
    @NotNull(message = "Հարկավոր է նշել մուտքի ամսաթիվը")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date inDate;
    @NotEmpty(message ="Հարկավոր է նշել մուտքի գումարը")
    private String inCash;
    private String note;


    public WalletIn() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
