package com.example.secondaryDev.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.entity.security.AsAuthCompanyWebService;

@Repository
public interface AsAuthCompanyWebServiceRepository extends JpaRepository<AsAuthCompanyWebService, String> {
    List<AsAuthCompanyWebService> findBySECURITYGROUPGUID(String securityGroupGuid);
    List<AsAuthCompanyWebService> findBySECURITYGROUPGUIDAndCOMPANYGUID(String securityGroupGuid, String companyGuid);
}
