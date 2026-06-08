package com.example.secondaryDev.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.entity.security.AsAuthPlanInquiry;

@Repository
public interface AsAuthPlanInquiryRepository extends JpaRepository<AsAuthPlanInquiry, String> {
    List<AsAuthPlanInquiry> findBySECURITYGROUPGUID(String securityGroupGuid);
    List<AsAuthPlanInquiry> findBySECURITYGROUPGUIDAndCOMPANYGUIDAndPLANGUID(String securityGroupGuid, String companyGuid, String planGuid);
}
