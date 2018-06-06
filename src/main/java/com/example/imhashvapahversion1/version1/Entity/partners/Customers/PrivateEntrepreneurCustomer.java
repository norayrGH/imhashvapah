package com.example.imhashvapahversion1.version1.Entity.partners.Customers;



import com.example.imhashvapahversion1.version1.Entity.GeneralMethods;
import com.example.imhashvapahversion1.version1.Entity.Organization;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;


@Entity
public class PrivateEntrepreneurCustomer  implements GeneralMethods {
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
    private String bankAccount;
    @NotEmpty(message = "հասցեն պարտադիր է:")
    private String address;
    @ManyToOne
    private Organization organization;

    public PrivateEntrepreneurCustomer() {
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

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
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
        return "PrivateEntrepreneurCustomer";
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

    @Override
    public String getName() {
        return getPrivateEntrepreneurName();
    }

    @Override
    public Long getInnerId() {
        return getId();
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
        return null;
    }
}
