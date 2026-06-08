package com.example.secondaryDev.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.entity.AsTransaction;

@Repository
public interface AsTransactionSecondaryDevRepository extends JpaRepository<AsTransaction, String> {
    List<AsTransaction> findByPRODUCTGUID(String productGuid);
    List<AsTransaction> findByPLANGUID(String planGuid);
}
