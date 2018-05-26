package com.example.imhashvapahversion1.version1.repository.suppliers;

import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.formHelpClasses.supplier.SupplierClientOrganization;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SupplierClientOrganizationRepository extends CrudRepository<SupplierClientOrganization,Long> {
    @Query( "Select c from SupplierClientOrganization c where c.clientOrganizationName like :clientOrganizationName ")
    SupplierClientOrganization getByName(@Param("clientOrganizationName") String clientOrganizationName);
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false end FROM SupplierClientOrganization c WHERE c.clientOrganizationName=:clientOrganizationName")
    boolean existsByName(@Param("clientOrganizationName") String clientOrganizationName);
    }
