package com.example.secondaryDev.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.entity.security.AsAuthProduct;

@Repository
public interface AsAuthProductRepository extends JpaRepository<AsAuthProduct, String> {
    List<AsAuthProduct> findBySECURITYGROUPGUID(String securityGroupGuid);
    List<AsAuthProduct> findBySECURITYGROUPGUIDAndCOMPANYGUID(String securityGroupGuid, String companyGuid);
}
