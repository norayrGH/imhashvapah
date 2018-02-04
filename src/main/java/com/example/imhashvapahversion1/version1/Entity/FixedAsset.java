package com.example.imhashvapahversion1.version1.Entity;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.security.PrivateKey;
import java.util.Date;

@Entity
@Table(name = "tbl_fixedAsset")
public class FixedAsset {
    @Id
    @GeneratedValue
    private Long id;
    //Անվանում
    @Column(name = "fixedAsset_inventoryNumber", unique = true, nullable = false)
    @NotEmpty(message = "Հիմնական միջոցի գույքահամարը պարտադիր է: ")
    private String inventoryNumber;

    @Column(name = "fixedAsset_name", unique = false, nullable = false)
    @NotEmpty(message = "Հիմնական միջոցի անվանումը պարտադիր է: ")
    private String name;
    @Column(name = "fixedAsset_type", unique = false, nullable = false)
    @NotEmpty(message = " Հիմնական միջոցի տեսակը պարտադիր  ")
    private String type;
    @Column(name = "fixedAsset_acquiring_date", unique = false, nullable = false)
    @NotNull(message = "Ամսաթիվը պարտադիր է: ")

    private Date acquiringDate;
    @Column(name = "fixedAsset_acquiring_Amount", unique = false, nullable = false)
    @NotNull(message = " Ձեռքբերման գումարը պարտադիր է: ")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private int acquiringАmount;

    @Column(name = "fixedAsset_wear", unique = false, nullable = false)
    @NotNull(message = "Մաշվածքի տոկոսը պարտադիր է: ")
    private short wear;

    @Column(name = "fixedAsset_initialAccumulated", unique = false, nullable = false)
    @NotNull(message = " Սկզբնական կուտակված մաշվածքը պարտադիր է: ")
    private short  initialAccumulated;

    @Column(name = "fixedAsset_nout", unique = false, nullable = true)
    private String  nout;
    @Column(name = "fixedAsset_organizationId", unique = false, nullable = false)
    private long organizationId ;

    public FixedAsset() {
    }

    public FixedAsset(String inventoryNumber, String name, String type, Date acquiringDate, int acquiringАmount, short wear, short initialAccumulated, String nout, long organizationId) {
        this.inventoryNumber = inventoryNumber;
        this.name = name;
        this.type = type;
        this.acquiringDate = acquiringDate;
        this.acquiringАmount = acquiringАmount;
        this.wear = wear;
        this.initialAccumulated = initialAccumulated;
        this.nout = nout;
        this.organizationId = organizationId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(String inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getAcquiringDate() {
        return acquiringDate;
    }

    public void setAcquiringDate(Date acquiringDate) {
        this.acquiringDate = acquiringDate;
    }

    public int getAcquiringАmount() {
        return acquiringАmount;
    }

    public void setAcquiringАmount(int acquiringАmount) {
        this.acquiringАmount = acquiringАmount;
    }

    public short getWear() {
        return wear;
    }

    public void setWear(short wear) {
        this.wear = wear;
    }

    public short getInitialAccumulated() {
        return initialAccumulated;
    }

    public void setInitialAccumulated(short initialAccumulated) {
        this.initialAccumulated = initialAccumulated;
    }

    public String getNout() {
        return nout;
    }

    public void setNout(String nout) {
        this.nout = nout;
    }

    public long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(long organizationId) {
        this.organizationId = organizationId;
    }
}