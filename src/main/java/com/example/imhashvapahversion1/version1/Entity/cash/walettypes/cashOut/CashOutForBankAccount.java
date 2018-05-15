package com.example.imhashvapahversion1.version1.Entity.cash.walettypes.cashOut;

import com.example.imhashvapahversion1.version1.Entity.Organization;
import com.example.imhashvapahversion1.version1.Entity.cash.BankAccount;
import com.example.imhashvapahversion1.version1.Entity.cash.WalletOut;

import javax.persistence.*;
import javax.validation.Valid;
@Entity
public class CashOutForBankAccount {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Organization organization;
    @ManyToOne
    private BankAccount bankAccount;
    @OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @Valid
    private WalletOut walletOut;

    public CashOutForBankAccount() {
    }

    public CashOutForBankAccount(Organization organization, BankAccount bankAccount, WalletOut walletOut) {
        this.organization = organization;
        this.bankAccount = bankAccount;
        this.walletOut = walletOut;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public WalletOut getWalletOut() {
        return walletOut;
    }

    public void setWalletOut(WalletOut walletOut) {
        this.walletOut = walletOut;
    }
}
