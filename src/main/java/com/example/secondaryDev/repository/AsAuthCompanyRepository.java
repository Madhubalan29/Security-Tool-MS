package com.example.secondaryDev.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.entity.security.AsAuthCompany;
import com.example.entity.security.AsAuthCompanyId;

@Repository
public interface AsAuthCompanyRepository extends JpaRepository<AsAuthCompany, AsAuthCompanyId> {
    List<AsAuthCompany> findBySECURITYGROUPGUID(String securityGroupGuid);
}
