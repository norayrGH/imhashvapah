package com.example.imhashvapahversion1.version1.Entity.cash.waletintypes.formHelpClasses;

import com.example.imhashvapahversion1.version1.Entity.Organization;
import com.example.imhashvapahversion1.version1.Entity.cash.WalletIn;

import javax.persistence.*;

@Entity
public class ClientOrganization {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    String clientOrganizationName;

    @ManyToOne
    private Organization organization ;

    public ClientOrganization() {
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
