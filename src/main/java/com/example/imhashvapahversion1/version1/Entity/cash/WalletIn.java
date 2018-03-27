package com.example.imhashvapahversion1.version1.Entity.cash;

import com.example.imhashvapahversion1.version1.Entity.Organization;
import com.example.imhashvapahversion1.version1.Entity.cash.waletintypes.*;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;

import javax.persistence.Entity;

import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
public class WalletIn<T> {

    @Id
    @GeneratedValue
    private Long id;


    @OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @Valid
    private CashInFromSaleOfGoods cashInFromSaleOfGoods;


   /* @Any(metaColumn = @Column(name = "what_i_contain"))
    @Cascade(CascadeType.ALL)
    @AnyMetaDef(
            idType = "long",
            metaType = "string",
            metaValues = {
                    @MetaValue(value = "CashInFromBankAccount", targetEntity = CashInFromBankAccount.class),
                    @MetaValue(value = "CashInFromLoan", targetEntity = CashInFromLoan.class),
                    @MetaValue(value = "CashInFromServiceProvision", targetEntity = CashInFromServiceProvision.class),
                    @MetaValue(value = "CashInFromPointOfSale", targetEntity = CashInFromPointOfSale.class),

                    @MetaValue(value = "CashInFromCredit", targetEntity = CashInFromCredit.class)
            })
    @JoinColumn(name = "property_id")

    private T t;*/
    @NotNull(message = "Հարկավոր է նշել մուտքի ամսաթիվը")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date inDate;
    @NotEmpty(message ="Հարկավոր է նշել մուտքի գումարը")
    private String inCash;
    private String note;
    @ManyToOne
    private Organization organization;

    public WalletIn() {
    }



    public WalletIn(CashInFromSaleOfGoods cashInFromSaleOfGoods, T t, Date inDate, String inCash, String note, Organization organization) {
        this.cashInFromSaleOfGoods = cashInFromSaleOfGoods;

        this.inDate = inDate;
        this.inCash = inCash;
        this.note = note;
        this.organization = organization;
    }

    public CashInFromSaleOfGoods getCashInFromSaleOfGoods() {
        return cashInFromSaleOfGoods;
    }

    public void setCashInFromSaleOfGoods(CashInFromSaleOfGoods cashInFromSaleOfGoods) {
        this.cashInFromSaleOfGoods = cashInFromSaleOfGoods;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }





    public Date getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public String getInCash() {
        return inCash;
    }

    public void setInCash(String inCash) {
        this.inCash = inCash;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
