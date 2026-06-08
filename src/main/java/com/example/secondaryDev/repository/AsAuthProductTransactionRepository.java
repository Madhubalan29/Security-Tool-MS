package com.example.secondaryDev.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.entity.security.AsAuthProductTransaction;

@Repository
public interface AsAuthProductTransactionRepository extends JpaRepository<AsAuthProductTransaction, String> {
    List<AsAuthProductTransaction> findBySECURITYGROUPGUID(String securityGroupGuid);
    List<AsAuthProductTransaction> findBySECURITYGROUPGUIDAndCOMPANYGUIDAndPRODUCTGUID(String securityGroupGuid, String companyGuid, String productGuid);
}
