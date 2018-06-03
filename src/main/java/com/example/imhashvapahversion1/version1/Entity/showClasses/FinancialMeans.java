package com.example.imhashvapahversion1.version1.Entity.showClasses;

public class FinancialMeans {
    String name ;
    Long openingBalance ;
    Long in ;
    Long out ;
    Long finalBalance;


    public FinancialMeans() {
    }

    public FinancialMeans(String name, Long openingBalance, Long in, Long out, Long finalBalance) {
        this.name = name;
        this.openingBalance = openingBalance;
        this.in = in;
        this.out = out;
        this.finalBalance = finalBalance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(Long openingBalance) {
        this.openingBalance = openingBalance;
    }

    public Long getIn() {
        return in;
    }

    public void setIn(Long in) {
        this.in = in;
    }

    public Long getOut() {
        return out;
    }

    public void setOut(Long out) {
        this.out = out;
    }

    public Long getFinalBalance() {
        return finalBalance;
    }

    public void setFinalBalance(Long finalBalance) {
        this.finalBalance = finalBalance;
    }
}
