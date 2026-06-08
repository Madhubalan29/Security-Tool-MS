package com.example.secondaryDev.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.entity.security.AsAuthPlanPage;

@Repository
public interface AsAuthPlanPageRepository extends JpaRepository<AsAuthPlanPage, String> {
    List<AsAuthPlanPage> findBySECURITYGROUPGUID(String securityGroupGuid);
    List<AsAuthPlanPage> findBySECURITYGROUPGUIDAndCOMPANYGUIDAndPLANGUID(String securityGroupGuid, String companyGuid, String planGuid);
}
