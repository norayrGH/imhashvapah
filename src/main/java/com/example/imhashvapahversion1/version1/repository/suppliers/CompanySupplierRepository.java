package com.example.imhashvapahversion1.version1.repository.suppliers;

import com.example.imhashvapahversion1.version1.Entity.partners.suppliers.CompanySupplier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface CompanySupplierRepository  extends CrudRepository<CompanySupplier,Long>{
    @Query("Select c from CompanySupplier c WHERE c.supply='Ապրանք/Ծառայություն'")
    ArrayList findBySupplyGoodsAndServices();
    @Query("Select c from CompanySupplier c WHERE c.supply='Վարձակալության/ծառայություն'")
    ArrayList findBySupplyForRent();


    @Query("Select c from CompanySupplier c WHERE c.hvhh is not null " )
    ArrayList findByHvhhNotNull( );

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM CompanySupplier c WHERE c.hvhh like :hvhh" )
    boolean existsByHvhh( @Param("hvhh")String hvhh );
    @Query( "Select c.id from CompanySupplier c where c.hvhh like :hvhh ")
    Long getIdByHvhh (@Param("hvhh")String hvhh );
}
