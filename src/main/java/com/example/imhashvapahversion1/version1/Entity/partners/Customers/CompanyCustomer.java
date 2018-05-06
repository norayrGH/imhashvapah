package com.example.imhashvapahversion1.version1.Entity.partners.Customers;

import com.example.imhashvapahversion1.version1.Entity.GeneralMethods;
import com.example.imhashvapahversion1.version1.Entity.Organization;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.formHelpClasses.customer.CustomerClientOrganization;
import com.example.imhashvapahversion1.version1.validate.test.EqualFields;

import javax.persistence.*;
import javax.validation.Valid;


@Entity
@EqualFields(id = "id", uniqueField = "clientOrganization",hvhh = "hvhh")
public class CompanyCustomer  implements GeneralMethods {
    @Id
    @GeneratedValue
    private Long id ;
    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    private CustomerClientOrganization clientOrganization;

    private String hvhh;
    private String taxType;
    private String openingBalanceType;
    private String openingBalance;
    private String bankAccount;
    private String address;
    private String phoneNumber;
    @ManyToOne
    private Organization organization;

    public CompanyCustomer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerClientOrganization getClientOrganization() {
        return clientOrganization;
    }

    public void setClientOrganization(CustomerClientOrganization clientOrganization) {
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

    @Override
    public String getName() {
        return clientOrganization.getClientOrganizationName();
    }

    @Override
    public Long getInnerId() {
        return clientOrganization.getId();
    }

    @Override
    public Long getClientOrganizationId() {
        return clientOrganization.getId();
    }

    @Override
    public Long getIndividualId() {
        return null;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }
}
