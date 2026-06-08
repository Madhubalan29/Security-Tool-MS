package com.example.secondaryDev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.entity.AsAuthWebService;

@Repository
public interface AsAuthWebServiceSecondaryDevRepository extends JpaRepository<AsAuthWebService, String> {
}
