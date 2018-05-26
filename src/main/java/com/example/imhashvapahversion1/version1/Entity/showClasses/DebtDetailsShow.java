package com.example.imhashvapahversion1.version1.Entity.showClasses;

import java.util.Date;

public class DebtDetailsShow {
    private Date debtDate;
    private String contents;
    private Integer payment;
    private Integer purchase;
    private Long id ;

    public DebtDetailsShow() {
    }

    public DebtDetailsShow(Date debtDate, String contents, Integer payment, Integer purchase, Long id) {
        this.debtDate = debtDate;
        this.contents = contents;
        this.payment = payment;
        this.purchase = purchase;
        this.id = id;
    }

    public Date getDebtDate() {
        return debtDate;
    }

    public void setDebtDate(Date debtDate) {
        this.debtDate = debtDate;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Integer getPayment() {
        return payment;
    }

    public void setPayment(Integer payment) {
        this.payment = payment;
    }

    public Integer getPurchase() {
        return purchase;
    }

    public void setPurchase(Integer purchase) {
        this.purchase = purchase;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
