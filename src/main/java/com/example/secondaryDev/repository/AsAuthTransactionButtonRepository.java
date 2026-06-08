package com.example.secondaryDev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.entity.security.AsAuthTransactionButton;
import com.example.entity.security.AsAuthTransactionButtonId;
import java.util.List;

@Repository
public interface AsAuthTransactionButtonRepository extends JpaRepository<AsAuthTransactionButton, AsAuthTransactionButtonId> {
    List<AsAuthTransactionButton> findBySECURITYGROUPGUID(String securityGroupGuid);
}
