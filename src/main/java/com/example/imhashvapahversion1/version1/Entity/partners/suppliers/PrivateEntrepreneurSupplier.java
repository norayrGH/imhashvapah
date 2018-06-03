package com.example.imhashvapahversion1.version1.Entity.partners.suppliers;

import com.example.imhashvapahversion1.version1.Entity.GeneralMethods;
import com.example.imhashvapahversion1.version1.Entity.Organization;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PrivateEntrepreneurSupplier extends Supplier implements GeneralMethods {
    @Id
    @GeneratedValue
    private Long id ;
    @NotEmpty(message = "Անվանումը պարտադիր է:")
    private String privateEntrepreneurName ;
    @NotEmpty(message = "ՀՎՀՀ-ն պարտադիր է:")
    private String hvhh;
    @NotEmpty(message = "Հարկման կարգը պարտադիր է:")
    private String taxType;
    private String openingBalanceType;
    @NotEmpty(message = "Սկզբնական մնացորդը պարտադիր է:")
    private String openingBalance;
    private String supply;
    private String bankAccount;
    private String address;
    private String phoneNumber;

    @ManyToOne
    private Organization organization;

    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        return privateEntrepreneurName;
    }

    @Override
    public Long getInnerId() {
        return null;
    }

    @Override
    public Long getClientOrganizationId() {
        return null;
    }

    @Override
    public Long getIndividualId() {
        return null;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
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

    @Override
    public String getHch() {
        return null;
    }

    @Override
    public String getType() {
        return "PrivateEntrepreneurSupplier";
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

    public String getSupply() {
        return supply;
    }

    public void setSupply(String supply) {
        this.supply = supply;
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

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
