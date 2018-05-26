package com.example.imhashvapahversion1.version1.Entity.partners.otherPartner;

import com.example.imhashvapahversion1.version1.Entity.GeneralMethods;
import com.example.imhashvapahversion1.version1.Entity.Organization;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Pattern;

@Entity
public class PrivateEntrepreneurOtherPartner implements GeneralMethods {
    @Id
    @GeneratedValue
    private Long id ;
    private String privateEntrepreneurName ;
    @NotEmpty(message = "հարկավոր է մուտքագրել ՀՎՀՀ-ն")
    private String hvhh;
    private String taxType;
    private String groupPayer;
    @Pattern(regexp = "prepaid",message = "Անհնար է վարկատու կամ փոխատու գործընկերոջը տալ կանխավճար։ Խնդրում ենք ընտրել պարտք տեսակը սկզբնական մնացորդների համար, եթե այն նույնիսկ զրո է։")
    private String openingBalanceType;
    private String openingBalance;
    private String bankAccount;
    private String address;
    private String phoneNumber;
    @ManyToOne
    private Organization organization;

    public PrivateEntrepreneurOtherPartner() {
    }

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
    public String getType() {
        return "PrivateEntrepreneurOtherPartner";
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
