package com.example.imhashvapahversion1.version1.Entity.cash.walettypes.cashIn;

import com.example.imhashvapahversion1.version1.Entity.Organization;
import com.example.imhashvapahversion1.version1.Entity.cash.WalletIn;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.GetWaletIn;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.Valid;

@Entity
public class CashInFromBankAccount implements GetWaletIn {
    @Id
    @GeneratedValue
    private Long id;
    @NotEmpty(message= "Հարկավոր է ընտրել հաշվեհամարը ")
    private String bankAccount;
    @ManyToOne
    private Organization organization;

    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    private WalletIn  walletIn;
    public CashInFromBankAccount() {

    }
    public CashInFromBankAccount(String bankAccount, Organization organization, WalletIn walletIn) {
        this.bankAccount = bankAccount;
        this.organization = organization;
        this.walletIn = walletIn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public WalletIn getWalletIn() {
        return walletIn;
    }
    @Override
    public WalletIn getWalletInImpl() {
        return walletIn;
    }

    public void setWalletIn(WalletIn walletIn) {
        this.walletIn = walletIn;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
