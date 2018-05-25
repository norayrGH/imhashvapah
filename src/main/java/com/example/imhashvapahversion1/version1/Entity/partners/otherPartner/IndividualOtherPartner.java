package com.example.imhashvapahversion1.version1.Entity.partners.otherPartner;

import com.example.imhashvapahversion1.version1.Entity.GeneralMethods;
import com.example.imhashvapahversion1.version1.Entity.Organization;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.formHelpClasses.otherPartner.OtherPartnerIndividual;

import javax.persistence.*;
import javax.validation.Valid;
import java.sql.Date;
@Entity
public class IndividualOtherPartner  implements GeneralMethods {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    private OtherPartnerIndividual individual;
    private Date bDate;
    private String hch;
    private String groupPayer;
    private String passportNumber;
    private String passportViewingDate;
    private String openingBalanceType;
    private String openingBalance;
    private String address;
    private String phoneNumber;

    @ManyToOne
    private Organization organization;

    public IndividualOtherPartner() {
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        return individual.getName();
    }

    @Override
    public Long getInnerId() {
        return individual.getId();
    }

    @Override
    public Long getClientOrganizationId() {
        return null;
    }

    @Override
    public Long getIndividualId() {
        return individual.getId();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OtherPartnerIndividual getIndividual() {
        return individual;
    }

    public void setIndividual(OtherPartnerIndividual individual) {
        this.individual = individual;
    }

    public Date getbDate() {
        return bDate;
    }

    public void setbDate(Date bDate) {
        this.bDate = bDate;
    }

    public String getHch() {
        return hch;
    }

    public void setHch(String hch) {
        this.hch = hch;
    }

    public String getGroupPayer() {
        return groupPayer;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setGroupPayer(String groupPayer) {
        this.groupPayer = groupPayer;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPassportViewingDate() {
        return passportViewingDate;
    }

    public void setPassportViewingDate(String passportViewingDate) {
        this.passportViewingDate = passportViewingDate;
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

    public String getAddress() {
        return address;
    }

    @Override
    public String getHvhh() {
        return null;
    }

    @Override
    public String getType() {
        return "IndividualOtherPartner";
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
