package com.example.imhashvapahversion1.version1.Entity.cash.walettypes.formHelpClasses.supplier;

import com.example.imhashvapahversion1.version1.Entity.GeneralMethods;
import com.example.imhashvapahversion1.version1.Entity.Organization;
import com.example.imhashvapahversion1.version1.validate.Unique;
import com.example.imhashvapahversion1.version1.validate.test.EqualFields;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@EqualFields(id = "id", uniqueField = "clientOrganizationName",hvhh = "", message = "")
public class SupplierClientOrganization implements GeneralMethods {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Անվան դաշտը հարկավոր է լրացնել:")
    private String clientOrganizationName;
    @ManyToOne
    private Organization organization;

    public SupplierClientOrganization() {
    }

    public SupplierClientOrganization(String clientOrganizationName, Organization organization) {
        this.clientOrganizationName = clientOrganizationName;
        this.organization = organization;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        return clientOrganizationName;
    }

    @Override
    public Long getInnerId() {
        return id;
    }

    @Override
    public Long getClientOrganizationId() {
        return null;
    }

    @Override
    public Long getIndividualId() {
        return null;
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

    @Override
    public String getHch() {
        return null;
    }

    @Override
    public String getType() {
        return "SupplierClientOrganization";
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
