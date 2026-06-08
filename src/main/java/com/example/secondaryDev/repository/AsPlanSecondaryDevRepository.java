package com.example.secondaryDev.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.entity.AsPlan;

@Repository
public interface AsPlanSecondaryDevRepository extends JpaRepository<AsPlan, String> {
    List<AsPlan> findByCOMPANYGUIDAndPRODUCTGUID(String companyGuid, String productGuid);
    List<AsPlan> findByCOMPANYGUID(String companyGuid);
}
