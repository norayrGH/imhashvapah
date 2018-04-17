package com.example.imhashvapahversion1.version1.Entity.cash.walettypes.formHelpClasses;

import com.example.imhashvapahversion1.version1.Entity.GeneralMethods;
import com.example.imhashvapahversion1.version1.Entity.Organization;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
public class ClientOrganization implements GeneralMethods {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Կազմակերպության անվանումը պարտադիր է ")
    private  String clientOrganizationName;
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
    public String display (){

        return getClientOrganizationName() ;
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

    @Override
    public String getName() {
        return clientOrganizationName;
    }

    @Override
    public String getPhoneNumber() {
        return null;
    }

    @Override
    public String getAddress() {
        return null;
    }

    @Override
    public String getHvhh() {
        return null;
    }
}
