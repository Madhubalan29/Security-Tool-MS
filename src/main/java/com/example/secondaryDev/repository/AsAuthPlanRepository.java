package com.example.secondaryDev.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.entity.security.AsAuthPlan;

@Repository
public interface AsAuthPlanRepository extends JpaRepository<AsAuthPlan, String> {
    List<AsAuthPlan> findBySECURITYGROUPGUID(String securityGroupGuid);
    List<AsAuthPlan> findBySECURITYGROUPGUIDAndCOMPANYGUID(String securityGroupGuid, String companyGuid);
}
