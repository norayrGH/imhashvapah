package com.example.imhashvapahversion1.version1.repository;

import com.example.imhashvapahversion1.version1.Entity.cash.waletintypes.CashInFromBankAccount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import java.sql.Date;
import java.util.ArrayList;



public interface CashInFromBankAccountRepository extends CrudRepository<CashInFromBankAccount,Long> {

    @Query("Select  c from CashInFromBankAccount c INNER JOIN c.walletIn w WHERE w.inDate>=:startDate ")
    ArrayList findByRangeStart(@Param("startDate") Date startDate);
    @Query( "Select c from CashInFromBankAccount c INNER JOIN c.walletIn w WHERE w.inDate BETWEEN :startDate AND :endDate  ")
    ArrayList findByRange(@Param("startDate") Date startDate , @Param("endDate") Date endDate);
    @Query( "Select c from CashInFromBankAccount c INNER JOIN c.walletIn w WHERE w.inDate<=:endDate ")
    ArrayList findByEnd(@Param("endDate")Date endDate);
}
