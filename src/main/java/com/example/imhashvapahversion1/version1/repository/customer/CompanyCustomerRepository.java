package com.example.imhashvapahversion1.version1.repository.customer;

import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.formHelpClasses.customer.CustomerClientOrganization;
import com.example.imhashvapahversion1.version1.Entity.partners.Customers.CompanyCustomer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;


public interface CompanyCustomerRepository  extends CrudRepository<CompanyCustomer,Long>{
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM CompanyCustomer c WHERE c.id=:parenId and c.clientOrganization.id=:innerId")
    boolean existsById( @Param("parenId")Long parenId , @Param("innerId") Long innerId );
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM CompanyCustomer c WHERE c.hvhh like :hvhh" )
    boolean existsByHvhh( @Param("hvhh")String hvhh );
    @Query( "Select c.id from CompanyCustomer c where c.hvhh like :hvhh ")
    Long getIdByHvhh (@Param("hvhh")String hvhh );

    @Query("Select c from CompanyCustomer c WHERE c.hvhh is not null " )
    ArrayList findByHvhhNotNull( );

}
