package com.example.imhashvapahversion1.version1.repository.cashOut;

import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.cashOut.CashOutForSerivceProvider;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.Date;

public interface CashOutForSerivceProviderRepository extends CrudRepository<CashOutForSerivceProvider,Long>{
    @Query("Select c from CashOutForSerivceProvider c INNER JOIN c.walletOut w WHERE w.outDate<=:startDate ")
    ArrayList findByRangeStart(@Param("startDate") Date startDate);
}
