package com.example.imhashvapahversion1.version1.repository.cashIn;

import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.cashIn.CashInFromServiceProvision;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.ArrayList;

public interface CashInFromServiceProvisionRepository extends CrudRepository<CashInFromServiceProvision,Long> {
    @Query("Select c from CashInFromServiceProvision c INNER JOIN c.walletIn w WHERE w.inDate>=:startDate ")
    ArrayList findByRangeStart(@Param("startDate") Date startDate);
    @Query( "Select c from CashInFromServiceProvision c INNER JOIN c.walletIn w WHERE w.inDate>=:startDate and w.inDate<=:endDate ")
    ArrayList findByRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
    @Query( "Select c from CashInFromServiceProvision c INNER JOIN c.walletIn w WHERE w.inDate<=:endDate ")
    ArrayList findByEnd(@Param("endDate")Date endDate);

    @Query("Select c from CashInFromServiceProvision c INNER JOIN c.walletIn w WHERE w.inDate<=:endDate ")
    ArrayList findByRangeEnd(@Param("endDate") Date endDate);


    @Query("Select c from CashInFromServiceProvision c INNER JOIN c.walletIn w INNER JOIN c.companyCustomer s  WHERE w.inDate>=:startDate and w.inDate<=:endDate AND s.id=:companyCustomerId")
    ArrayList findByRangeAndCompanyCustomerId(@Param("startDate") Date startDate,@Param("endDate") Date endDate,@Param("companyCustomerId") Long companyCustomerId);






    @Query("Select c from CashInFromServiceProvision c INNER JOIN c.walletIn w INNER JOIN c.individualCustomer s  WHERE w.inDate>=:startDate and w.inDate<=:endDate AND s.id=:individualCustomerId")
    ArrayList findByRangeAndIndividualCustomerId(@Param("startDate") Date startDate,@Param("endDate") Date endDate,@Param("individualCustomerId") Long individualCustomerId);









    @Query("Select c from CashInFromServiceProvision c INNER JOIN c.walletIn w INNER JOIN c.privateEntrepreneurCustomer s  WHERE w.inDate>=:startDate and w.inDate<=:endDate AND s.id=:privateEntrepreneurCustomerId")
    ArrayList findByRangeAndPrivateEntrepreneurCustomerId(@Param("startDate") Date startDate,@Param("endDate") Date endDate,@Param("privateEntrepreneurCustomerId") Long privateEntrepreneurCustomerId);



}
