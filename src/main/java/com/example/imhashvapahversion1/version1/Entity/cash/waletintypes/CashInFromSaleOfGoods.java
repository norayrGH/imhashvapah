package com.example.imhashvapahversion1.version1.Entity.cash.waletintypes;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
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
                    @MetaValue(value = "ClientOrganization", targetEntity = CashInFromBankAccount.class),
                    @MetaValue(value = "Individual", targetEntity = CashInFromLoan.class)
            })
    @JoinColumn(name = "property_id")
    private T customer ;

    private Date ContractDate ;

    private String ContractNubmer ;

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
}
