package com.example.imhashvapahversion1.version1.Entity.cash.waletintypes.cashIn;

import com.example.imhashvapahversion1.version1.Entity.Organization;
import com.example.imhashvapahversion1.version1.Entity.cash.WalletIn;
import com.example.imhashvapahversion1.version1.Entity.cash.waletintypes.GetWaletIn;

import javax.persistence.*;
import javax.validation.Valid;

@Entity
public class CashInFromPointOfSale implements GetWaletIn {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private Organization organization;

    @OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @Valid
    private WalletIn walletIn;


    public CashInFromPointOfSale(WalletIn walletIn, Organization organization) {
        this.walletIn = walletIn;
        this.organization = organization;
    }

    public CashInFromPointOfSale() {
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
