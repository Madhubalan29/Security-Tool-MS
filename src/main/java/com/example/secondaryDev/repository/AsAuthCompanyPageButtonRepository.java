package com.example.secondaryDev.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.entity.security.AsAuthCompanyPageButton;

@Repository
public interface AsAuthCompanyPageButtonRepository extends JpaRepository<AsAuthCompanyPageButton, String> {
    List<AsAuthCompanyPageButton> findBySECURITYGROUPGUID(String securityGroupGuid);
    List<AsAuthCompanyPageButton> findBySECURITYGROUPGUIDAndCOMPANYGUID(String securityGroupGuid, String companyGuid);
}
