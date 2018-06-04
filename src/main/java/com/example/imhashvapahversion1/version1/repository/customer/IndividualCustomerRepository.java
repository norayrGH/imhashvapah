package com.example.imhashvapahversion1.version1.repository.customer;

import com.example.imhashvapahversion1.version1.Entity.FixedAsset;
import com.example.imhashvapahversion1.version1.Entity.partners.Customers.IndividualCustomer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public interface IndividualCustomerRepository extends CrudRepository<IndividualCustomer,Long> {
    @Query("Select c from IndividualCustomer c WHERE c.hch is not null " )
    ArrayList findByHchNotNull( );
}
