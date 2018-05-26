package com.example.imhashvapahversion1.version1.repository.otherpartners;

import com.example.imhashvapahversion1.version1.Entity.partners.otherPartner.CompanyOtherPartner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CompanyOtherPartnerRepository  extends CrudRepository<CompanyOtherPartner,Long>{
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM  CompanyOtherPartner c WHERE c.id=:parenId and c.clientOrganization.id=:innerId")
    boolean existsById(@Param("parenId")Long parenId , @Param("innerId") Long innerId );
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM CompanyOtherPartner c WHERE c.hvhh like :hvhh" )
    boolean existsByHvhh( @Param("hvhh")String hvhh );
    @Query( "Select c.id from CompanyOtherPartner c where c.hvhh like :hvhh ")
    Long getIdByHvhh (@Param("hvhh")String hvhh );
}
