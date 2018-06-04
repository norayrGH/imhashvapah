package com.example.imhashvapahversion1.version1.repository.customer;

import com.example.imhashvapahversion1.version1.Entity.partners.Customers.Sale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.Date;

public interface SaleRepository extends CrudRepository<Sale,Long> {
    @Query("Select s from Sale s   WHERE s.saleDate>=:startDate ")
    ArrayList findByRangeStart(@Param("startDate") Date startDate);

    @Query( "Select s from Sale s  WHERE s.saleDate>=:startDate and s.saleDate<=:endDate ")
    ArrayList findByRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);


    @Query( "Select s from Sale s  WHERE s.saleDate<=:endDate ")
    ArrayList findByEnd(@Param("endDate")Date endDate);




    @Query("Select s from Sale s INNER JOIN s.companyCustomer su  WHERE s.saleDate>=:startDate and s.saleDate<=:endDate AND su.id=:companyCustomerId")
    ArrayList findByRangeAndCompanyCustomerId(@Param("startDate") Date startDate,@Param("endDate") Date endDate,@Param("companyCustomerId") Long companyCustomerId);



    @Query("Select s from Sale s INNER JOIN s.individualCustomer su  WHERE s.saleDate>=:startDate and s.saleDate<=:endDate AND su.id=:individualCustomerId")
    ArrayList findByRangeAndIndividualCustomerId(@Param("startDate") Date startDate,@Param("endDate") Date endDate,@Param("individualCustomerId") Long individualCustomerId);


    @Query("Select s from Sale s INNER JOIN s.privateEntrepreneurCustomer su  WHERE s.saleDate>=:startDate and s.saleDate<=:endDate AND su.id=:privateEntrepreneurCustomerId")
    ArrayList findByRangeAndPrivateEntrepreneurCustomerId(@Param("startDate") Date startDate,@Param("endDate") Date endDate,@Param("privateEntrepreneurCustomerId") Long privateEntrepreneurCustomerId);





}
