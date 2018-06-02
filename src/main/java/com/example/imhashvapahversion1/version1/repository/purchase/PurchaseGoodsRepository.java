package com.example.imhashvapahversion1.version1.repository.purchase;

import com.example.imhashvapahversion1.version1.Entity.partners.purchase.PurchaseGoods;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.Date;

public interface PurchaseGoodsRepository extends CrudRepository<PurchaseGoods,Long> {
    @Query("Select p from PurchaseGoods p  WHERE p.purchaseDate>=:startDate ")
    ArrayList findByRangeStart(@Param("startDate") Date startDate);
    @Query("Select p from PurchaseGoods p  WHERE p.purchaseDate<=:endDate ")
    ArrayList findByRangeEnd(@Param("endDate") Date endDate);
    @Query("Select p from PurchaseGoods p INNER JOIN p.companySupplier s  WHERE p.purchaseDate>=:startDate AND s.id=:companySupplierId")
    ArrayList findByRangeStartAndCompanySupplierId(@Param("startDate") Date startDate,@Param("companySupplierId") Long companySupplierId);
    @Query("Select p from PurchaseGoods p INNER JOIN p.companySupplier s  WHERE p.purchaseDate<=:endDate AND s.id=:companySupplierId")
    ArrayList findByRangeEndAndCompanySupplierId(@Param("endDate") Date endDate,@Param("companySupplierId") Long companySupplierId);
    @Query("Select p from PurchaseGoods p INNER JOIN p.companySupplier s  WHERE p.purchaseDate>=:startDate and p.purchaseDate<=:endDate AND s.id=:companySupplierId")
    ArrayList findByRangeAndCompanySupplierId(@Param("startDate") Date startDate,@Param("endDate") Date endDate,@Param("companySupplierId") Long companySupplierId);

    //IndividualSupplier
    @Query("Select p from PurchaseGoods p INNER JOIN p.individualSupplier s  WHERE p.purchaseDate>=:startDate AND s.id=:individualSupplierId")
    ArrayList findByRangeStartAndIndividualSupplierId(@Param("startDate") Date startDate,@Param("individualSupplierId") Long individualSupplierId);
    @Query("Select p from PurchaseGoods p INNER JOIN p.individualSupplier s  WHERE p.purchaseDate<=:endDate AND s.id=:individualSupplierId")
    ArrayList findByRangeEndAndIndividualSupplierId(@Param("endDate") Date endDate,@Param("individualSupplierId") Long individualSupplierId);
    @Query("Select p from PurchaseGoods p INNER JOIN p.individualSupplier s  WHERE p.purchaseDate>=:startDate and p.purchaseDate<=:endDate AND s.id=:individualSupplierId")
    ArrayList findByRangeAndIndividualSupplierId(@Param("startDate") Date startDate,@Param("endDate") Date endDate,@Param("individualSupplierId") Long individualSupplierId);

    //PrivateEntrepreneurSupplier
    @Query("Select p from PurchaseGoods p INNER JOIN p.privateEntrepreneurSupplier s  WHERE p.purchaseDate>=:startDate AND s.id=:privateEntrepreneurSupplierId")
    ArrayList findByRangeStartAndPrivateEntrepreneurSupplierId(@Param("startDate") Date startDate,@Param("privateEntrepreneurSupplierId") Long privateEntrepreneurSupplierId);
    @Query("Select p from PurchaseGoods p INNER JOIN p.privateEntrepreneurSupplier s  WHERE p.purchaseDate<=:endDate AND s.id=:privateEntrepreneurSupplierId")
    ArrayList findByRangeEndAndPrivateEntrepreneurSupplierId(@Param("endDate") Date endDate,@Param("privateEntrepreneurSupplierId") Long privateEntrepreneurSupplierId);
    @Query("Select p from PurchaseGoods p INNER JOIN p.privateEntrepreneurSupplier s  WHERE p.purchaseDate>=:startDate and p.purchaseDate<=:endDate AND s.id=:privateEntrepreneurSupplierId")
    ArrayList findByRangeAndPrivateEntrepreneurSupplierId(@Param("startDate") Date startDate,@Param("endDate") Date endDate,@Param("privateEntrepreneurSupplierId") Long privateEntrepreneurSupplierId);

}
