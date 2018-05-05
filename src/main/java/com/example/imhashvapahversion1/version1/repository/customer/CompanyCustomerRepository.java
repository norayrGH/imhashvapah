package com.example.imhashvapahversion1.version1.repository.customer;

import com.example.imhashvapahversion1.version1.Entity.partners.Customers.CompanyCustomer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;



public interface CompanyCustomerRepository  extends CrudRepository<CompanyCustomer,Long>{
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM CompanyCustomer c WHERE c.id=:parenId and c.customerClientOrganization.id=:innerId")
    boolean existsById( @Param("parenId")Long parenId , @Param("innerId") Long innerId );
}
