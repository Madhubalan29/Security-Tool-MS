package com.example.secondaryDev.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.entity.security.AsAuthCompanyPage;

@Repository
public interface AsAuthCompanyPageRepository extends JpaRepository<AsAuthCompanyPage, String> {
    List<AsAuthCompanyPage> findBySECURITYGROUPGUID(String securityGroupGuid);
    List<AsAuthCompanyPage> findBySECURITYGROUPGUIDAndCOMPANYGUID(String securityGroupGuid, String companyGuid);
}
