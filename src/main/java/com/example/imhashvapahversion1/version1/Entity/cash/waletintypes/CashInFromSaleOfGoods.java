package com.example.imhashvapahversion1.version1.Entity.cash.waletintypes;

import com.example.imhashvapahversion1.version1.Entity.cash.WalletIn;
import com.example.imhashvapahversion1.version1.Entity.cash.waletintypes.formHelpClasses.ClientOrganization;
import com.example.imhashvapahversion1.version1.Entity.cash.waletintypes.formHelpClasses.Individual;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;


import javax.persistence.*;
import javax.persistence.Entity;

import java.sql.Date;

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
                    @MetaValue(value = "ClientOrganization", targetEntity = ClientOrganization.class),
                    @MetaValue(value = "Individual", targetEntity = Individual.class)
            })
    @JoinColumn(name = "property_id")

    private T customer ;

    private Date ContractDate ;

    private String ContractNubmer ;

    @ManyToOne
    private WalletIn walletIn;

    public CashInFromSaleOfGoods() {
    }

    public CashInFromSaleOfGoods(T t) {
        this.customer = t;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public T getT() {
        return customer;
    }

    public void setT(T t) {
        this.customer = t;
    }

    public Date getContractDate() {
        return ContractDate;
    }

    public void setContractDate(Date contractDate) {
        ContractDate = contractDate;
    }

    public String getContractNubmer() {
        return ContractNubmer;
    }

    public void setContractNubmer(String contractNubmer) {
        ContractNubmer = contractNubmer;
    }

    public T getCustomer() {
        return customer;
    }

    public void setCustomer(T customer) {
        this.customer = customer;
    }

    public WalletIn getWalletIn() {
        return walletIn;
    }

    public void setWalletIn(WalletIn walletIn) {
        this.walletIn = walletIn;
    }
}
