package com.example.imhashvapahversion1.version1.repository.customer;

import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.formHelpClasses.customer.CustomerClientOrganization;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CustomerClientOrganizationRepository extends CrudRepository<CustomerClientOrganization,Long> {
    @Query( "Select c from CustomerClientOrganization c where c.clientOrganizationName like :clientOrganizationName ")
    CustomerClientOrganization getByName( @Param("clientOrganizationName")String clientOrganizationName );

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false end FROM CustomerClientOrganization c WHERE c.clientOrganizationName=:clientOrganizationName")
    boolean existsByName( @Param("clientOrganizationName")String clientOrganizationName );

    }
