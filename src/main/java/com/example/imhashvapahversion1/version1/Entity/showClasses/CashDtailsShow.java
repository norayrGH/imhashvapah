package com.example.imhashvapahversion1.version1.Entity.showClasses;

import java.util.Date;

public class CashDtailsShow  implements Comparable<CashDtailsShow> {
    private Date cashDate;
    private Long cashId;
    private String cashIn;
    private String cashOut;
    private String type;

    public CashDtailsShow() {
    }

    public CashDtailsShow(Date cashDate, Long cashId, String cashIn, String cashOut, String type) {
        this.cashDate = cashDate;
        this.cashId = cashId;
        this.cashIn = cashIn;
        this.cashOut = cashOut;
        this.type = type;
    }

    public Date getCashDate() {
        return cashDate;
    }

    public void setCashDate(Date cashDate) {
        this.cashDate = cashDate;
    }

    public Long getCashId() {
        return cashId;
    }

    public void setCashId(Long cashId) {
        this.cashId = cashId;
    }

    public String getCashIn() {
        return cashIn;
    }

    public void setCashIn(String cashIn) {
        this.cashIn = cashIn;
    }

    public String getCashOut() {
        return cashOut;
    }

    public void setCashOut(String cashOut) {
        this.cashOut = cashOut;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @Override
    public int compareTo(CashDtailsShow o) {
        return getCashDate().compareTo(o.getCashDate());
    }


}
