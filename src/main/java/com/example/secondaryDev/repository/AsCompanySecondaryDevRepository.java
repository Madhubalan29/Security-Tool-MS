package com.example.secondaryDev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.entity.AsCompany;

@Repository
public interface AsCompanySecondaryDevRepository extends JpaRepository<AsCompany, String> {
}
