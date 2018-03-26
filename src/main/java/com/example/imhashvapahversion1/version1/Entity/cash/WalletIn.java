package com.example.imhashvapahversion1.version1.Entity.cash;

import com.example.imhashvapahversion1.version1.Entity.Organization;
import com.example.imhashvapahversion1.version1.Entity.cash.waletintypes.*;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
public class WalletIn<T> {

    @Id
    @GeneratedValue
    private Long id;

    @Any(metaColumn = @Column(name = "what_i_contain"))
    @Cascade(CascadeType.ALL)
    @AnyMetaDef(
            idType = "long",
            metaType = "string",
            metaValues = {
                    @MetaValue(value = "CashInFromBankAccount", targetEntity = CashInFromBankAccount.class),
                    @MetaValue(value = "CashInFromLoan", targetEntity = CashInFromLoan.class),
                    @MetaValue(value = "CashInFromServiceProvision", targetEntity = CashInFromServiceProvision.class),
                    @MetaValue(value = "CashInFromPointOfSale", targetEntity = CashInFromPointOfSale.class),
                    @MetaValue(value = "CashInFromSaleOfGoods", targetEntity = CashInFromSaleOfGoods.class),
                    @MetaValue(value = "CashInFromCredit", targetEntity = CashInFromCredit.class)
            })
    @JoinColumn(name = "property_id")
    @NotNull(message = "Հարկավոր է նշել գնորդին")
    private T t;
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

    public WalletIn(T t) {
        this.t = t;
    }

    public WalletIn(T t, Date inDate, String inCash, String note) {
        this.t = t;
        this.inDate = inDate;
        this.inCash = inCash;
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
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
