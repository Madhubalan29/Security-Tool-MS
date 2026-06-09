package com.example.secondaryDev.repository;

import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.entity.security.AsAuthProductPageButton;

@Repository
public interface AsAuthProductPageButtonRepository extends JpaRepository<AsAuthProductPageButton, String> {
    interface Projection {
        String getAUTHPRODUCTPAGEGUID();
        String getAUTHBUTTONGUID();
    }
    @Query("select b.AUTHPRODUCTPAGEGUID as AUTHPRODUCTPAGEGUID, b.AUTHBUTTONGUID as AUTHBUTTONGUID " +
           "from AsAuthProductPageButton b " +
           "join AsAuthProductPage p on b.AUTHPRODUCTPAGEGUID = p.AUTHPRODUCTPAGEGUID " +
           "join AsAuthProduct ap on p.AUTHPRODUCTGUID = ap.AUTHPRODUCTGUID " +
           "join AsAuthCompany c on ap.AUTHCOMPANYGUID = c.AUTHCOMPANYGUID " +
           "where c.SECURITYGROUPGUID = :securityGroupGuid")
    List<Projection> findBySecurityGroupGuid(@Param("securityGroupGuid") String securityGroupGuid);
}
