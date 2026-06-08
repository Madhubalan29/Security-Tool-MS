package com.example.secondaryDev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.entity.security.AsAuthProductTransactionButton;
import com.example.entity.security.AsAuthProductTransactionButtonId;
import java.util.List;

@Repository
public interface AsAuthProductTransactionButtonRepository extends JpaRepository<AsAuthProductTransactionButton, AsAuthProductTransactionButtonId> {
    List<AsAuthProductTransactionButton> findBySECURITYGROUPGUID(String securityGroupGuid);
}
