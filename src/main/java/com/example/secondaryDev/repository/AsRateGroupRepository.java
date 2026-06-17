package com.example.secondaryDev.repository;

import com.example.entity.AsRateGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsRateGroupRepository extends JpaRepository<AsRateGroup, String> {

    @Query("SELECT DISTINCT a.rateDescription FROM AsRateGroup a WHERE a.rateDescription IS NOT NULL ORDER BY a.rateDescription ASC")
    List<String> findDistinctRateDescriptions();

    boolean existsByRateDescription(String rateDescription);

    AsRateGroup findByRateDescription(String rateDescription);
}
