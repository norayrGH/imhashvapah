package com.example.imhashvapahversion1.version1.repository;

import com.example.imhashvapahversion1.version1.Entity.FixedAsset;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UniversalRepository extends CrudRepository<FixedAsset,Long> {
    @Query( "Select f from FixedAsset f where f.acquiringDate between ?1 and current_date ")
    List<FixedAsset> findByRange(@Param("startDate") Date startDate);
}
