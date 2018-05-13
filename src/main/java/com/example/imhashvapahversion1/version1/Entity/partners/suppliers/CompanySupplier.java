package com.example.imhashvapahversion1.version1.Entity.partners.suppliers;

import com.example.imhashvapahversion1.version1.Entity.GeneralMethods;
import com.example.imhashvapahversion1.version1.Entity.Organization;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.formHelpClasses.supplier.SupplierClientOrganization;

import javax.persistence.*;
import javax.validation.Valid;

@Entity
public class CompanySupplier extends Supplier implements GeneralMethods  {
    @Id
    @GeneratedValue
    private Long id ;
    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    private SupplierClientOrganization clientOrganization;

    private String hvhh;
    private String taxType;
    private String openingBalanceType;
    private String openingBalance;
    private String supply;
    private String bankAccount;
    private String address;
    private String phoneNumber;
    @ManyToOne
    private Organization organization;

    public CompanySupplier() {
    }

    @Override
    public Long getId() {
        return id;
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

    public void setId(Long id) {
        this.id = id;
    }

    public SupplierClientOrganization getClientOrganization() {
        return clientOrganization;
    }

    public void setClientOrganization(SupplierClientOrganization clientOrganization) {
        this.clientOrganization = clientOrganization;
    }

    @Override
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

    @Override
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
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
