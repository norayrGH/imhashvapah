package com.example.imhashvapahversion1.version1.repository.cashOut;

import com.example.imhashvapahversion1.version1.Entity.cash.WalletOut;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.Date;

public interface WalletOutRepository  extends CrudRepository<WalletOut,Long>{
    @Query("Select sum(w.outCash) from WalletOut w where w.outDate>=:startDate and w.outDate<=:endDate ")
    Long returnSumOfOutsByRange(@Param("startDate") Date startDate , @Param("endDate") Date endDate);
    @Query("Select sum(w.outCash) from WalletOut w where w.outDate<:startDate")
    Long returnSumOfOutsForFinalBalanceSum(@Param("startDate") Date startDate);


}
