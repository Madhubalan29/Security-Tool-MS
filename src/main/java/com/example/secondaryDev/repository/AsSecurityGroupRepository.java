package com.example.secondaryDev.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.entity.security.AsSecurityGroup;

@Repository
public interface AsSecurityGroupRepository extends JpaRepository<AsSecurityGroup, String> {
}
