package com.example.imhashvapahversion1.version1.repository;

import com.example.imhashvapahversion1.version1.Entity.FixedAsset;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.formHelpClasses.ClientOrganization;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface ClientOrganizationRepository extends CrudRepository<ClientOrganization,Long> {
    @Query( "Select c from ClientOrganization c where c.clientOrganizationName like :clientOrganizationName ")
    ClientOrganization getByName( @Param("clientOrganizationName")String clientOrganizationName );
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM ClientOrganization c WHERE c.clientOrganizationName=:companyName")
    boolean existsByName( @Param("clientOrganizationName")String clientOrganizationName );
}
