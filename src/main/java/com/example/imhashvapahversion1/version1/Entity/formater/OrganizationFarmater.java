package com.example.imhashvapahversion1.version1.Entity.formater;

import com.example.imhashvapahversion1.version1.Entity.Organization;
import com.example.imhashvapahversion1.version1.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class OrganizationFarmater implements Formatter<Organization> {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    public String print(Organization organization, Locale locale) {
        return organization.getId().toString();
    }


    @Override
    public Organization parse(String organizationId,Locale locale) throws ParseException {
        return organizationRepository.findOne( Long.parseLong(organizationId));
    }


}
