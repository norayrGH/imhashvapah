package com.example.imhashvapahversion1.version1.Entity.cash.walettypes.formHelpClasses.customer;

import com.example.imhashvapahversion1.version1.Entity.Organization;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
public class CustomerIndividual {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Անվան դաշտը անպայման է լրացնել ")
    private String firstName;
    @NotEmpty(message = "Ազգանուն դաշտը անպայման է լրացնել ")
    private String lastName;
    @ManyToOne
    private Organization organization ;

    public CustomerIndividual() {
    }

    public CustomerIndividual(String firstName, String lastName, Organization organization) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.organization = organization;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
