package com.example.imhashvapahversion1.version1.repository.otherpartners;

import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.formHelpClasses.otherPartner.OtherPartnerClientOrganization;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface OtherPartnerClientOrganizationRepository extends CrudRepository<OtherPartnerClientOrganization,Long> {
    @Query( "Select c from OtherPartnerClientOrganization c where c.clientOrganizationName like :clientOrganizationName ")
    OtherPartnerClientOrganization getByName(@Param("clientOrganizationName") String clientOrganizationName);
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false end FROM OtherPartnerClientOrganization c WHERE c.clientOrganizationName=:clientOrganizationName")
    boolean existsByName(@Param("clientOrganizationName") String clientOrganizationName);
    }
