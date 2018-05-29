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
    @Query("Select c from CashOutForSerivceProvider c INNER JOIN c.walletOut w INNER JOIN c.companySupplier s  WHERE w.outDate>=:startDate AND s.id=:companySupplierId")
    ArrayList findByRangeStartAndCompanySupplierId(@Param("startDate") Date startDate,@Param("companySupplierId") Long companySupplierId);
    @Query("Select c from CashOutForSerivceProvider c INNER JOIN c.walletOut w INNER JOIN c.companySupplier s  WHERE w.outDate<=:endDate AND s.id=:companySupplierId")
    ArrayList findByRangeEndAndCompanySupplierId(@Param("endDate") Date endDate,@Param("companySupplierId") Long companySupplierId);
    @Query("Select c from CashOutForSerivceProvider c INNER JOIN c.walletOut w INNER JOIN c.companySupplier s  WHERE w.outDate>=:startDate and w.outDate<=:endDate AND s.id=:companySupplierId")
    ArrayList findByRangeAndCompanySupplierId(@Param("startDate") Date startDate,@Param("endDate") Date endDate,@Param("companySupplierId") Long companySupplierId);

    //IndividualSupplier
    @Query("Select c from CashOutForSerivceProvider c INNER JOIN c.walletOut w INNER JOIN c.individualSupplier s  WHERE w.outDate>=:startDate AND s.id=:individualSupplierId")
    ArrayList findByRangeStartAndIndividualSupplierId(@Param("startDate") Date startDate,@Param("individualSupplierId") Long individualSupplierId);
    @Query("Select c from CashOutForSerivceProvider c INNER JOIN c.walletOut w INNER JOIN c.individualSupplier s  WHERE w.outDate<=:endDate AND s.id=:individualSupplierId")
    ArrayList findByRangeEndAndIndividualSupplierId(@Param("endDate") Date endDate,@Param("individualSupplierId") Long individualSupplierId);
    @Query("Select c from CashOutForSerivceProvider c INNER JOIN c.walletOut w INNER JOIN c.individualSupplier s  WHERE w.outDate>=:startDate and w.outDate<=:endDate AND s.id=:individualSupplierId")
    ArrayList findByRangeAndIndividualSupplierId(@Param("startDate") Date startDate,@Param("endDate") Date endDate,@Param("individualSupplierId") Long individualSupplierId);

    //PrivateEntrepreneurSupplier
    @Query("Select c from CashOutForSerivceProvider c INNER JOIN c.walletOut w INNER JOIN c.privateEntrepreneurSupplier s  WHERE w.outDate>=:startDate AND s.id=:privateEntrepreneurSupplierId")
    ArrayList findByRangeStartAndPrivateEntrepreneurSupplierId(@Param("startDate") Date startDate,@Param("privateEntrepreneurSupplierId") Long privateEntrepreneurSupplierId);
    @Query("Select c from CashOutForSerivceProvider c INNER JOIN c.walletOut w INNER JOIN c.privateEntrepreneurSupplier s  WHERE w.outDate<=:endDate AND s.id=:privateEntrepreneurSupplierId")
    ArrayList findByRangeEndAndPrivateEntrepreneurSupplierId(@Param("endDate") Date endDate,@Param("privateEntrepreneurSupplierId") Long privateEntrepreneurSupplierId);
    @Query("Select c from CashOutForSerivceProvider c INNER JOIN c.walletOut w INNER JOIN c.privateEntrepreneurSupplier s  WHERE w.outDate>=:startDate and w.outDate<=:endDate AND s.id=:privateEntrepreneurSupplierId")
    ArrayList findByRangeAndPrivateEntrepreneurSupplierId(@Param("startDate") Date startDate,@Param("endDate") Date endDate,@Param("privateEntrepreneurSupplierId") Long privateEntrepreneurSupplierId);


}
