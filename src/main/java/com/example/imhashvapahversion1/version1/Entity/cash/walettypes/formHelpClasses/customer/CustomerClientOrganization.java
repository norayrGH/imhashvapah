package com.example.imhashvapahversion1.version1.Entity.cash.walettypes.formHelpClasses.customer;

import com.example.imhashvapahversion1.version1.Entity.Organization;

import javax.persistence.*;

@Entity
public class CustomerClientOrganization {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //@Unique(service = ClientOrganizationService.class, fieldName = "clientOrganizationName", message = "Գնորդի անվանումը չի կարող կրկնվել:")
    private String clientOrganizationName;
    @ManyToOne
    private Organization organization;




}
