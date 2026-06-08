package com.example.secondaryDev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.entity.AsAuthButton;

@Repository
public interface AsAuthButtonSecondaryDevRepository extends JpaRepository<AsAuthButton, String> {
}
