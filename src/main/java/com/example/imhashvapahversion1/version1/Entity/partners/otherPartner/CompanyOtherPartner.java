package com.example.imhashvapahversion1.version1.Entity.partners.otherPartner;

import com.example.imhashvapahversion1.version1.Entity.Organization;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.formHelpClasses.otherPartner.OtherPartnerClientOrganization;
import com.example.imhashvapahversion1.version1.validate.test.EqualFields;

import javax.persistence.*;
import javax.validation.Valid;

@Entity
@EqualFields(id = "id", uniqueField = "clientOrganization",hvhh = "hvhh" )
public class CompanyOtherPartner {
    @Id
    @GeneratedValue
    private Long id ;
    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    private OtherPartnerClientOrganization clientOrganization;
    private String hvhh;
    private String taxType;
    private String groupPayer;
    private String openingBalanceType;
    private String openingBalance;
    private String bankAccount;
    private String address;
    private String phoneNumber;

    @ManyToOne
    private Organization organization;

    public CompanyOtherPartner() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OtherPartnerClientOrganization getClientOrganization() {
        return clientOrganization;
    }

    public void setClientOrganization(OtherPartnerClientOrganization clientOrganization) {
        this.clientOrganization = clientOrganization;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
