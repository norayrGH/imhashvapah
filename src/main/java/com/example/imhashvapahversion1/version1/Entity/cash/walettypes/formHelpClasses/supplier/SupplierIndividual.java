package com.example.imhashvapahversion1.version1.Entity.cash.walettypes.formHelpClasses.supplier;

import com.example.imhashvapahversion1.version1.Entity.Organization;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
public class SupplierIndividual {
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
