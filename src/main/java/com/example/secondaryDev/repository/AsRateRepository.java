package com.example.secondaryDev.repository;

import com.example.entity.AsRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AsRateRepository extends JpaRepository<AsRate, String> {
    List<AsRate> findByRateDescriptionIn(List<String> rateDescriptions);
    
    @Modifying
    @Query("DELETE FROM AsRate a WHERE a.rateDescription IN :rateDescriptions")
    void bulkDeleteByRateDescriptionIn(@Param("rateDescriptions") List<String> rateDescriptions);
    
    boolean existsByRateGuid(String rateGuid);
    
    @Query("SELECT DISTINCT a.rateDescription FROM AsRate a WHERE a.rateDescription IS NOT NULL ORDER BY a.rateDescription ASC")
    List<String> findDistinctRateDescriptions();
}
