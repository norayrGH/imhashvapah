package com.example.imhashvapahversion1.version1.Entity.cash.walettypes.cashOut;

import com.example.imhashvapahversion1.version1.Entity.Organization;
import com.example.imhashvapahversion1.version1.Entity.cash.WalletOut;

import javax.persistence.*;
import javax.validation.Valid;
@Entity
public class CashOutForOtherExpenses {

    @Id
    @GeneratedValue
    private Long id;

    private String bankCommissions;
    @ManyToOne
    private Organization organization;
    @OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @Valid
    private WalletOut walletOut;

    public CashOutForOtherExpenses() {
    }

    public CashOutForOtherExpenses(String bankCommissions, Organization organization, WalletOut walletOut) {
        this.bankCommissions = bankCommissions;
        this.organization = organization;
        this.walletOut = walletOut;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBankCommissions() {
        return bankCommissions;
    }

    public void setBankCommissions(String bankCommissions) {
        this.bankCommissions = bankCommissions;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public WalletOut getWalletOut() {
        return walletOut;
    }

    public void setWalletOut(WalletOut walletOut) {
        this.walletOut = walletOut;
    }
}
