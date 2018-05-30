package com.example.imhashvapahversion1.version1.Entity.partners.suppliers;

import com.example.imhashvapahversion1.version1.Entity.GeneralMethods;
import com.example.imhashvapahversion1.version1.Entity.Organization;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.formHelpClasses.supplier.SupplierClientOrganization;
import com.example.imhashvapahversion1.version1.validate.test.EqualFields;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.Valid;
import java.io.Serializable;

@Entity
@EqualFields(id = "id", uniqueField = "clientOrganization",hvhh = "hvhh" ,message = "")
public class CompanySupplier implements GeneralMethods {

    @Id
    @GeneratedValue
    private Long id ;
    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    private SupplierClientOrganization clientOrganization;
    @NotEmpty(message = "ՀՎՀՀ-ն պարտադիր է:")
    private String hvhh;
    @NotEmpty(message = "Հարկման կարգը պարտադիր է:")
    private String taxType;
    @NotEmpty(message = "Սկզբնական մնացորդի տեսակը պարտադիր է:")
    private String openingBalanceType;
    @NotEmpty(message = "Սկզբնական մնացորդը պարտադիր է:")
    private String openingBalance;
    @NotEmpty
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

    @Override
    public String getType() {
        return "CompanySupplier";
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
