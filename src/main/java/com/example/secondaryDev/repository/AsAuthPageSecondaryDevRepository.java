package com.example.secondaryDev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.entity.AsAuthPage;

@Repository
public interface AsAuthPageSecondaryDevRepository extends JpaRepository<AsAuthPage, String> {
}
