package com.example.imhashvapahversion1.version1.repository.cashIn;

import com.example.imhashvapahversion1.version1.Entity.cash.WalletIn;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.Date;

public interface WalletInRepository extends CrudRepository<WalletIn, Long> {

    @Query("Select sum(w.inCash) from WalletIn w where w.inDate>=:startDate")
    Long returnSumOfInsByStart(@Param("startDate") Date startDate);
    @Query("Select sum(w.inCash) from WalletIn w where w.inDate>=:startDate and w.inDate<=:endDate ")
    Long returnSumOfInsByRange(@Param("startDate") Date startDate , @Param("endDate") Date endDate);
    @Query("Select sum(w.inCash) from WalletIn w where w.inDate<=:endDate")
    Long returnSumOfInsByEnd(@Param("endDate") Date endDate);
    @Query("Select sum(w.inCash) from WalletIn w ")
    Long returnAllSumOfIns();

    @Query("Select sum(w.inCash) from WalletIn w where w.inDate<:startDate")
    Long returnSumOfInsForOpeningBalance(@Param("startDate") Date startDate);
}
