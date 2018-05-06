package com.example.imhashvapahversion1.version1.repository.otherpartners;

import com.example.imhashvapahversion1.version1.Entity.partners.otherPartner.CompanyOtherPartner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CompanyOtherPartnerRepository  extends CrudRepository<CompanyOtherPartner,Long>{
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM  CompanyOtherPartner c WHERE c.id=:parenId and c.clientOrganization.id=:innerId")
    boolean existsById(@Param("parenId")Long parenId , @Param("innerId") Long innerId );
}
