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
    private String privateEntrepreneurName ;
    @NotEmpty
    private String hvhh;
    private String taxType;
    private String openingBalanceType;
    private String openingBalance;
    private String bankAccount;
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
