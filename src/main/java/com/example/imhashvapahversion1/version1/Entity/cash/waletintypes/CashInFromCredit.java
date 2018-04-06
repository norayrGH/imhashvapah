package com.example.imhashvapahversion1.version1.Entity.cash.waletintypes;

import com.example.imhashvapahversion1.version1.Entity.Organization;
import com.example.imhashvapahversion1.version1.Entity.cash.WalletIn;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.Valid;

@Entity
public class CashInFromCredit {

    @Id
    @GeneratedValue
    private int id;
    @NotEmpty(message = "հարկավոր է ընտրել գործընկերոջը ")
    private String colleagues;
    @OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @Valid
    private WalletIn walletIn;
    @ManyToOne
    private Organization organization;

    public CashInFromCredit(String colleagues, WalletIn walletIn, Organization organization) {
        this.colleagues = colleagues;
        this.walletIn = walletIn;
        this.organization = organization;
    }

    public CashInFromCredit() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColleagues() {
        return colleagues;
    }

    public void setColleagues(String colleagues) {
        this.colleagues = colleagues;
    }

    public WalletIn getWalletIn() {
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
