package com.example.secondaryDev.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.entity.security.AsAuthPlanPageButton;

@Repository
public interface AsAuthPlanPageButtonRepository extends JpaRepository<AsAuthPlanPageButton, String> {
    List<AsAuthPlanPageButton> findBySECURITYGROUPGUID(String securityGroupGuid);
    List<AsAuthPlanPageButton> findBySECURITYGROUPGUIDAndCOMPANYGUIDAndPLANGUID(String securityGroupGuid, String companyGuid, String planGuid);
}
