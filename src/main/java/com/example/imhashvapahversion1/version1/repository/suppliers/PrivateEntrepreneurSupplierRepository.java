package com.example.imhashvapahversion1.version1.repository.suppliers;

import com.example.imhashvapahversion1.version1.Entity.partners.suppliers.PrivateEntrepreneurSupplier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface PrivateEntrepreneurSupplierRepository extends CrudRepository<PrivateEntrepreneurSupplier,Long> {
   @Query("Select p from PrivateEntrepreneurSupplier p WHERE p.supply='Ապրանք/Ծառայություն'")
   ArrayList findBySupplyGoodsAndServices();
   @Query("Select c from PrivateEntrepreneurSupplier c WHERE c.supply='Վարձակալության/ծառայություն'")
   ArrayList findBySupplyForRent();
}
