package com.example.imhashvapahversion1.version1.Entity.cash;

import com.example.imhashvapahversion1.version1.Entity.Organization;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class BankAccount {
    @Id
    @GeneratedValue
    private Long id;
    @NotEmpty( message = "մուտքագրեք հաշվեհամարը ճիշտ")
    private String bankAccount;
    @NotEmpty( message = "ճիշտ մուտքագրեք հաշվեհամարի առաջին հինգ նիշը ")
    private String bankName;
    @NotEmpty( message = "հարկավոր է ընտրել մնացորդի տիպը")
    private String openingBalanceType;
    @NotEmpty( message = "մնացորդի գումարը պարտադիր է ")
    private String openingBalance;
    @NotEmpty( message = "Մուտքագրեք դրական ամբողջ թիվ:")
    private String startOutNumbering;

    private String nout;
    @ManyToOne
    Organization organization;

    public BankAccount(String bankAccount, String bankName, String openingBalanceType, String openingBalance, String startOutNumbering, String nout, Organization organization) {
        this.bankAccount = bankAccount;
        this.bankName = bankName;
        this.openingBalanceType = openingBalanceType;
        this.openingBalance = openingBalance;
        this.startOutNumbering = startOutNumbering;
        this.nout = nout;
        this.organization = organization;
    }

    public BankAccount() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
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

    public String getStartOutNumbering() {
        return startOutNumbering;
    }

    public void setStartOutNumbering(String startOutNumbering) {
        this.startOutNumbering = startOutNumbering;
    }

    public String getNout() {
        return nout;
    }

    public void setNout(String nout) {
        this.nout = nout;
    }
}
