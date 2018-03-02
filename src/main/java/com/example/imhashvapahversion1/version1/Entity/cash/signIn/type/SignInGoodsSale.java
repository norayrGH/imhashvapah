package com.example.imhashvapahversion1.version1.Entity.cash.signIn.type;

import java.sql.Date;

public class SignInGoodsSale<T> {


    private T companyOrEmployee;
    private Date entherDate ;
    private String entherCash;

    public SignInGoodsSale() {
    }

    public SignInGoodsSale(T companyOrEmployee, Date entherDate, String entherCash) {
        this.companyOrEmployee = companyOrEmployee;
        this.entherDate = entherDate;
        this.entherCash = entherCash;
    }

    public T getCompanyOrEmployee() {
        return companyOrEmployee;
    }

    public void setCompanyOrEmployee(T companyOrEmployee) {
        this.companyOrEmployee = companyOrEmployee;
    }

    public Date getEntherDate() {
        return entherDate;
    }

    public void setEntherDate(Date entherDate) {
        this.entherDate = entherDate;
    }

    public String getEntherCash() {
        return entherCash;
    }

    public void setEntherCash(String entherCash) {
        this.entherCash = entherCash;
    }
}
