package com.example.imhashvapahversion1.version1.Entity.cash.walettypes.cashOut;

import com.example.imhashvapahversion1.version1.Entity.Organization;
import com.example.imhashvapahversion1.version1.Entity.cash.Tax;
import com.example.imhashvapahversion1.version1.Entity.cash.WalletOut;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.GetWaletOut;

import javax.persistence.*;
import javax.validation.Valid;
@Entity
public class CashOutForTax implements GetWaletOut {

    @Id
    @GeneratedValue
    private Long id;
    @OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @Valid
    private Tax tax;
    @ManyToOne
    private Organization organization;

    @OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @Valid
    private WalletOut walletOut;

    public CashOutForTax() {
    }

    public CashOutForTax(Tax tax, Organization organization, WalletOut walletOut) {
        this.tax = tax;
        this.organization = organization;
        this.walletOut = walletOut;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
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
    @Override
    public Long getCashOutId() {
        return id;
    }

    @Override
    public WalletOut getWalletOutImpl() {
        return walletOut;
    }
}
