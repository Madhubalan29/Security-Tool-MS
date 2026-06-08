package com.example.secondaryDev.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.entity.security.AsAuthTransaction;

@Repository
public interface AsAuthTransactionRepository extends JpaRepository<AsAuthTransaction, String> {
    List<AsAuthTransaction> findBySECURITYGROUPGUID(String securityGroupGuid);
    List<AsAuthTransaction> findBySECURITYGROUPGUIDAndCOMPANYGUIDAndPLANGUID(String securityGroupGuid, String companyGuid, String planGuid);
}
