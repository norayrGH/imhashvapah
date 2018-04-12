package com.example.imhashvapahversion1.version1.Entity.partners.otherPartner;

import com.example.imhashvapahversion1.version1.Entity.Organization;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PrivateEntrepreneurOtherPartner {
    @Id
    @GeneratedValue
    private Long id ;
    private String privateEntrepreneurName ;
    @NotEmpty
    private String hvhh;
    private String taxType;
    private String groupPayer;
    private String openingBalanceType;
    private String openingBalance;
    private String bankAccount;
    private String address;
    @ManyToOne
    private Organization organization;

    public PrivateEntrepreneurOtherPartner() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrivateEntrepreneurName() {
        return privateEntrepreneurName;
    }

    public void setPrivateEntrepreneurName(String privateEntrepreneurName) {
        this.privateEntrepreneurName = privateEntrepreneurName;
    }

    public String getHvhh() {
        return hvhh;
    }

    public void setHvhh(String hvhh) {
        this.hvhh = hvhh;
    }

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public String getGroupPayer() {
        return groupPayer;
    }

    public void setGroupPayer(String groupPayer) {
        this.groupPayer = groupPayer;
    }

    public String getOpeningBalanceType() {
        return openingBalanceType;
    }

    public void setOpeningBalanceType(String openingBalanceType) {
        this.openingBalanceType = openingBalanceType;
    }

    public String getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(String openingBalance) {
        this.openingBalance = openingBalance;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
