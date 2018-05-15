package com.example.imhashvapahversion1.version1.repository.suppliers;

import com.example.imhashvapahversion1.version1.Entity.partners.suppliers.IndividualSupplier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface IndividualSupplierRepository extends CrudRepository<IndividualSupplier,Long>{
    @Query("Select i from IndividualSupplier i WHERE i.supply='Ապրանք/Ծառայություն'")
    ArrayList findBySupplyGoodsAndServices();
    @Query("Select c from IndividualSupplier c WHERE c.supply='Վարձակալության/ծառայություն'")
    ArrayList findBySupplyForRent();
}
