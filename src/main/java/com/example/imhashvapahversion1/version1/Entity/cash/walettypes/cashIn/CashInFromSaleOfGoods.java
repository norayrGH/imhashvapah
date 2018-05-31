package com.example.imhashvapahversion1.version1.Entity.cash.walettypes.cashIn;

import com.example.imhashvapahversion1.version1.Entity.Organization;
import com.example.imhashvapahversion1.version1.Entity.cash.WalletIn;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.GetWaletIn;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.Valid;

import java.util.Date;

@Entity

public class CashInFromSaleOfGoods implements GetWaletIn {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "Գնորդի անունը պարտադիր է:")
    private String customerName;


    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date contractDate ;

    private String contractNubmer ;
    @OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @Valid
    private WalletIn walletIn;
    @ManyToOne
    private Organization organization;

    public CashInFromSaleOfGoods() {
    }

    public CashInFromSaleOfGoods(String customerName, Date contractDate, String contractNubmer, WalletIn walletIn, Organization organization) {
        this.customerName = customerName;
        this.contractDate = contractDate;
        this.contractNubmer = contractNubmer;
        this.walletIn = walletIn;
        this.organization = organization;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getContractDate() {
        return contractDate;
    }

    public void setContractDate(Date contractDate) {
        this.contractDate = contractDate;
    }

    public String getContractNubmer() {
        return contractNubmer;
    }

    public void setContractNubmer(String contractNubmer) {
        this.contractNubmer = contractNubmer;
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
