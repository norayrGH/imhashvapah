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
}
