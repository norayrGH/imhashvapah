package com.example.imhashvapahversion1.version1.Entity.cash.waletintypes.cashIn;

import com.example.imhashvapahversion1.version1.Entity.Organization;
import com.example.imhashvapahversion1.version1.Entity.cash.WalletIn;
import com.example.imhashvapahversion1.version1.Entity.cash.waletintypes.GetWaletIn;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.Date;

@Entity
public class CashInFromServiceProvision implements GetWaletIn {
    @Id
    @GeneratedValue
    private int id;
    @NotEmpty(message = "Գնորդի անունը պարտադիր է ")
    private String customerName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date contractDate ;


    private String contractNubmer ;

    @ManyToOne
    private Organization organization;
    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    private WalletIn walletIn;
    public CashInFromServiceProvision() {

    }

    public CashInFromServiceProvision(String customerName, Date contractDate, String contractNubmer, WalletIn walletIn, Organization organization) {
        this.customerName = customerName;
        this.contractDate = contractDate;
        this.contractNubmer = contractNubmer;
        this.walletIn = walletIn;
        this.organization = organization;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
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
}
