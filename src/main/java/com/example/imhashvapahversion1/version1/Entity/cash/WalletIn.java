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
@Inheritance(strategy=InheritanceType.JOINED)
public class WalletIn {

    @Id
    private Long id;
    @NotNull(message = "Հարկավոր է նշել մուտքի ամսաթիվը")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date inDate;
    @NotEmpty(message ="Հարկավոր է նշել մուտքի գումարը")
    private String inCash;
    private String note;


    public WalletIn() {
    }


}
