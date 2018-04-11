package com.example.imhashvapahversion1.version1.Entity.cash;

import com.example.imhashvapahversion1.version1.Entity.Organization;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Tax {
    @Id
    @GeneratedValue
    Long id;
    @NotEmpty(message = "ընտրեք հարկի տեսակը")
    private String taxType;
    @ManyToOne
    Organization organization;

    public Tax() {
    }

    public Tax(String taxType, Organization organization) {
        this.taxType = taxType;
        this.organization = organization;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
