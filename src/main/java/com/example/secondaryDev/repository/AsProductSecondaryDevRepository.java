package com.example.secondaryDev.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.entity.AsProduct;

@Repository
public interface AsProductSecondaryDevRepository extends JpaRepository<AsProduct, String> {
    List<AsProduct> findByCOMPANYGUID(String companyGuid);
}
