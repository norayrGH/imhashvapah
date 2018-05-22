package com.example.imhashvapahversion1.version1.repository.cashOut;


import com.example.imhashvapahversion1.version1.Entity.FixedAsset;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.cashOut.CashOutForGoodsProvider;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface CashOutForGoodsProviderRepository extends CrudRepository<CashOutForGoodsProvider,Long> {
    @Query("Select c from CashOutForGoodsProvider c INNER JOIN c.walletOut w WHERE w.outDate<=:startDate ")
    ArrayList findByRangeStart(@Param("startDate") Date startDate);
}
