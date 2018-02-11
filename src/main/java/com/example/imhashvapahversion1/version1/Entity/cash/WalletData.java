package com.example.imhashvapahversion1.version1.Entity.cash;

import com.example.imhashvapahversion1.version1.Entity.Organization;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
public class WalletData {
    @Id
    @GeneratedValue
    private Long id;
    @NotEmpty(message = "Դրամապանակի անվան դաշտը հարկավոր է լրացնել")
    private String walletName;
    private int startBalance;
    private int startingNumbering;
    private int outNumbering;
    private int cashBbookPapers;
    private int startingNumberCashBookPapers;
    private String notes;
    @OneToOne
    private Organization organization;

    public WalletData() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWalletName() {
        return walletName;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }

    public int getStartBalance() {
        return startBalance;
    }

    public void setStartBalance(int startBalance) {
        this.startBalance = startBalance;
    }

    public int getStartingNumbering() {
        return startingNumbering;
    }

    public void setStartingNumbering(int startingNumbering) {
        this.startingNumbering = startingNumbering;
    }

    public int getOutNumbering() {
        return outNumbering;
    }

    public void setOutNumbering(int outNumbering) {
        this.outNumbering = outNumbering;
    }

    public int getCashBbookPapers() {
        return cashBbookPapers;
    }

    public void setCashBbookPapers(int cashBbookPapers) {
        this.cashBbookPapers = cashBbookPapers;
    }

    public int getStartingNumberCashBookPapers() {
        return startingNumberCashBookPapers;
    }

    public void setStartingNumberCashBookPapers(int startingNumberCashBookPapers) {
        this.startingNumberCashBookPapers = startingNumberCashBookPapers;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
