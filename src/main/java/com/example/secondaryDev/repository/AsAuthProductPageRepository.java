package com.example.secondaryDev.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.entity.security.AsAuthProductPage;

@Repository
public interface AsAuthProductPageRepository extends JpaRepository<AsAuthProductPage, String> {
    List<AsAuthProductPage> findBySECURITYGROUPGUID(String securityGroupGuid);
    List<AsAuthProductPage> findBySECURITYGROUPGUIDAndCOMPANYGUIDAndPRODUCTGUID(String securityGroupGuid, String companyGuid, String productGuid);
}
