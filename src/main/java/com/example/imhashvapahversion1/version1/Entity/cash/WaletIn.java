package com.example.imhashvapahversion1.version1.Entity.cash;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class WaletIn {

    @Id
    @GeneratedValue
    private Long id ;
    @NotEmpty(message = "ընտրեք ")
  private String inType;
  private Date inDate;
  private String inCash;
  private String note;

    public WaletIn(String inType, Date inDate, String inCash, String note) {
        this.inType = inType;
        this.inDate = inDate;
        this.inCash = inCash;
        this.note = note;
    }

    public WaletIn() {
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
