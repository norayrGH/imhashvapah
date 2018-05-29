package com.example.imhashvapahversion1.version1.Entity.partners.service.rent;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PeriodicServiceRentOther {

    @Id
    @GeneratedValue
    private Long id;//Անվանում
    @NotEmpty(message = "Ծառայության անունը չի կարող լինել դատարկ:")
    private String name;
}
