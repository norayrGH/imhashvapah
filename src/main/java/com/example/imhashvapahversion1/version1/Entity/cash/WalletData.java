package com.example.imhashvapahversion1.version1.Entity.cash;

import com.example.imhashvapahversion1.version1.Entity.Organization;
import org.hibernate.validator.constraints.NotEmpty;


import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
public class WalletData {
    @Id
    @GeneratedValue
    private Long id;
    @NotEmpty(message = "Անունը պարտադիր է։")
    private String walletName;
    @NotNull(message = "Սկզբնական մնացորդը պարտադիր է։")
    private Integer startBalance;
    @NotNull(message = "Մուտքերի համարակալման առաջին թիվը պարտադիր է։")
    private Integer startingNumbering;
    @NotNull(message = "Ելքերի համարակալման առաջին թիվը պարտադիր է։")
    private Integer outNumbering;
    private Integer cashBbookPapers;
    @NotNull(message = "Համարակալման առաջին թիվը պարտադիր է։")
    private Integer startingNumberCashBookPapers;
    private String notes;
    @OneToOne
    private Organization organization;

    public WalletData() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWalletName() {
        return walletName;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }

    public Integer getStartBalance() {
        return startBalance;
    }

    public void setStartBalance(Integer startBalance) {
        this.startBalance = startBalance;
    }

    public Integer getStartingNumbering() {
        return startingNumbering;
    }

    public void setStartingNumbering(Integer startingNumbering) {
        this.startingNumbering = startingNumbering;
    }

    public Integer getOutNumbering() {
        return outNumbering;
    }

    public void setOutNumbering(Integer outNumbering) {
        this.outNumbering = outNumbering;
    }

    public Integer getCashBbookPapers() {
        return cashBbookPapers;
    }

    public void setCashBbookPapers(Integer cashBbookPapers) {
        this.cashBbookPapers = cashBbookPapers;
    }

    public Integer getStartingNumberCashBookPapers() {
        return startingNumberCashBookPapers;
    }

    public void setStartingNumberCashBookPapers(Integer startingNumberCashBookPapers) {
        this.startingNumberCashBookPapers = startingNumberCashBookPapers;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}