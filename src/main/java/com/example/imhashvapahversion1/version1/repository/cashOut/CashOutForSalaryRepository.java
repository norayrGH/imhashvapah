package com.example.imhashvapahversion1.version1.repository.cashOut;

import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.cashOut.CashOutForSalary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.Date;

public interface CashOutForSalaryRepository extends CrudRepository<CashOutForSalary,Long> {
    @Query( "Select c from CashOutForSalary c INNER JOIN c.walletOut w WHERE w.outDate BETWEEN :startDate AND :endDate  ")
    ArrayList findByRange(@Param("startDate") Date startDate , @Param("endDate") Date endDate);
    @Query("Select c from CashOutForSalary c INNER JOIN c.walletOut w WHERE w.outDate<=:endDate ")
    ArrayList findByRangeEnd(@Param("endDate") Date endDate);
    @Query("Select c from CashOutForSalary c INNER JOIN c.walletOut w WHERE w.outDate>=:startDate ")
    ArrayList findByRangeStart(@Param("startDate") Date startDate);

}
