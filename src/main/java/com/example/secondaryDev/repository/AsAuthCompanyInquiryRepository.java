package com.example.secondaryDev.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.entity.security.AsAuthCompanyInquiry;

@Repository
public interface AsAuthCompanyInquiryRepository extends JpaRepository<AsAuthCompanyInquiry, String> {
    List<AsAuthCompanyInquiry> findBySECURITYGROUPGUID(String securityGroupGuid);
    List<AsAuthCompanyInquiry> findBySECURITYGROUPGUIDAndCOMPANYGUID(String securityGroupGuid, String companyGuid);
}
