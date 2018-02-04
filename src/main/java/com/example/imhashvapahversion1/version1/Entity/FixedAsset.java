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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date acquiringDate;
    @Column(name = "fixedAsset_acquiring_Amount", unique = false, nullable = false)
    @NotEmpty(message = " Ձեռքբերման գումարը պարտադիր է: ")
    private String acquiringАmount;

    @Column(name = "fixedAsset_wear", unique = false, nullable = false)
    @NotEmpty(message = "Մաշվածքի տոկոսը պարտադիր է: ")
    private String wear;

    @Column(name = "fixedAsset_initialAccumulated", unique = false, nullable = false)
    @NotEmpty(message = " Սկզբնական կուտակված մաշվածքը պարտադիր է: ")
    private String  initialAccumulated;

    @Column(name = "fixedAsset_nout", unique = false, nullable = true)
    private String  nout;
    @Column(name = "fixedAsset_organizationId", unique = false, nullable = false)
    private Long organizationId ;


    public FixedAsset() {
    }

    public FixedAsset(String inventoryNumber, String name, String type, Date acquiringDate, String acquiringАmount, String wear, String initialAccumulated, String nout, Long organizationId) {
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

    public String getAcquiringАmount() {
        return acquiringАmount;
    }

    public void setAcquiringАmount(String acquiringАmount) {
        this.acquiringАmount = acquiringАmount;
    }

    public String getWear() {
        return wear;
    }

    public void setWear(String wear) {
        this.wear = wear;
    }

    public String getInitialAccumulated() {
        return initialAccumulated;
    }

    public void setInitialAccumulated(String initialAccumulated) {
        this.initialAccumulated = initialAccumulated;
    }

    public String getNout() {
        return nout;
    }

    public void setNout(String nout) {
        this.nout = nout;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }
}