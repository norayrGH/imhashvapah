package com.example.imhashvapahversion1.version1.Entity.cash.waletintypes;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
public class CashInFromSaleOfGoods<T>{
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private int id;
    @Cascade(CascadeType.ALL)
    @Any(metaColumn = @Column(name = "what_i_contain"))
    @AnyMetaDef(
            idType = "long",
            metaType = "string",
            metaValues = {
                    @MetaValue(value = "ClientOrganization", targetEntity = CashInFromBankAccount.class),
                    @MetaValue(value = "Individual", targetEntity = CashInFromLoan.class)
            })
    @JoinColumn(name = "property_id")
    private T t ;

    public CashInFromSaleOfGoods() {
    }

    public CashInFromSaleOfGoods(T t) {
        this.t = t;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
