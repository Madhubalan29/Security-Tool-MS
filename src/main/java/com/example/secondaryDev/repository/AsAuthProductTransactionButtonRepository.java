package com.example.secondaryDev.repository;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.entity.security.AsAuthProductTransactionButton;
import com.example.entity.security.AsAuthProductTransactionButtonId;
import java.util.List;

@Repository
public interface AsAuthProductTransactionButtonRepository extends JpaRepository<AsAuthProductTransactionButton, AsAuthProductTransactionButtonId> {
    interface Projection {
        String getAUTHPRODUCTTRANSACTIONGUID();
        String getAUTHBUTTONGUID();
    }
    @Query("select b.AUTHPRODUCTTRANSACTIONGUID as AUTHPRODUCTTRANSACTIONGUID, b.AUTHBUTTONGUID as AUTHBUTTONGUID " +
           "from AsAuthProductTransactionButton b " +
           "join AsAuthProductTransaction t on b.AUTHPRODUCTTRANSACTIONGUID = t.AUTHPRODUCTTRANSACTIONGUID " +
           "join AsAuthProduct ap on t.AUTHPRODUCTGUID = ap.AUTHPRODUCTGUID " +
           "join AsAuthCompany c on ap.AUTHCOMPANYGUID = c.AUTHCOMPANYGUID " +
           "where c.SECURITYGROUPGUID = :securityGroupGuid")
    List<Projection> findBySecurityGroupGuid(@Param("securityGroupGuid") String securityGroupGuid);
}
