package com.example.secondaryDev.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.entity.security.AsAuthProductPageButton;

@Repository
public interface AsAuthProductPageButtonRepository extends JpaRepository<AsAuthProductPageButton, String> {
    List<AsAuthProductPageButton> findBySECURITYGROUPGUID(String securityGroupGuid);
    List<AsAuthProductPageButton> findBySECURITYGROUPGUIDAndCOMPANYGUIDAndPRODUCTGUID(String securityGroupGuid, String companyGuid, String productGuid);
}
