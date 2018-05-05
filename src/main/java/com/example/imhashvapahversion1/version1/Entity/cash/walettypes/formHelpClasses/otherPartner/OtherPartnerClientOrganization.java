package com.example.imhashvapahversion1.version1.Entity.cash.walettypes.formHelpClasses.otherPartner;

import com.example.imhashvapahversion1.version1.Entity.Organization;

import javax.persistence.*;

@Entity
public class OtherPartnerClientOrganization {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //@Unique(service = ClientOrganizationService.class, fieldName = "clientOrganizationName", message = "Գնորդի անվանումը չի կարող կրկնվել:")
    private String clientOrganizationName;
    @ManyToOne
    private Organization organization;

    public OtherPartnerClientOrganization(String clientOrganizationName, Organization organization) {
        this.clientOrganizationName = clientOrganizationName;
        this.organization = organization;
    }

    public OtherPartnerClientOrganization() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientOrganizationName() {
        return clientOrganizationName;
    }

    public void setClientOrganizationName(String clientOrganizationName) {
        this.clientOrganizationName = clientOrganizationName;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
