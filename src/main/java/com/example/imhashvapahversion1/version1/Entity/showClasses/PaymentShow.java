package com.example.imhashvapahversion1.version1.Entity.showClasses;

import java.util.Date;

public class PaymentShow {
    Long id;
    Date paymentDate;
    Long paymentSum;
    String otherPartnerName;
    String cashinOrCashOut;

    public PaymentShow(Long id, Date paymentDate, Long paymentSum, String otherPartnerName, String cashinOrCashOut) {
        this.id = id;
        this.paymentDate = paymentDate;
        this.paymentSum = paymentSum;
        this.otherPartnerName = otherPartnerName;
        this.cashinOrCashOut = cashinOrCashOut;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Long getPaymentSum() {
        return paymentSum;
    }

    public void setPaymentSum(Long paymentSum) {
        this.paymentSum = paymentSum;
    }

    public String getOtherPartnerName() {
        return otherPartnerName;
    }

    public void setOtherPartnerName(String otherPartnerName) {
        this.otherPartnerName = otherPartnerName;
    }

    public String getCashinOrCashOut() {
        return cashinOrCashOut;
    }

    public void setCashinOrCashOut(String cashinOrCashOut) {
        this.cashinOrCashOut = cashinOrCashOut;
    }
}
