package com.example.imhashvapahversion1.version1.Entity.cash.walettypes;

import com.example.imhashvapahversion1.version1.Entity.Organization;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.formHelpClasses.Individual;

import javax.persistence.*;
import javax.validation.Valid;
import java.sql.Date;

@Entity
public class IndividualCustomer {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    private Individual individual;
    private Date bDate;
    private String hch;
    private String passportNumber;
    private String passportViewingDate;
    private String openingBalanceType;
    private String openingBalance;
    private String address;

    @ManyToOne
    private Organization organization;


    public IndividualCustomer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Individual getIndividual() {
        return individual;
    }

    public void setIndividual(Individual individual) {
        this.individual = individual;
    }

    public Date getbDate() {
        return bDate;
    }

    public void setbDate(Date bDate) {
        this.bDate = bDate;
    }

    public String getOpeningBalanceType() {
        return openingBalanceType;
    }

    public void setOpeningBalanceType(String openingBalanceType) {
        this.openingBalanceType = openingBalanceType;
    }

    public String getHch() {
        return hch;
    }

    public void setHch(String hch) {
        this.hch = hch;
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

    public String getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(String openingBalance) {
        this.openingBalance = openingBalance;
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
