package com.example.secondaryDev.repository;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.entity.security.AsAuthTransactionButton;
import com.example.entity.security.AsAuthTransactionButtonId;
import java.util.List;

@Repository
public interface AsAuthTransactionButtonRepository extends JpaRepository<AsAuthTransactionButton, AsAuthTransactionButtonId> {
    interface Projection {
        String getAUTHTRANSACTIONGUID();
        String getAUTHBUTTONGUID();
    }
    @Query("select b.AUTHTRANSACTIONGUID as AUTHTRANSACTIONGUID, b.AUTHBUTTONGUID as AUTHBUTTONGUID " +
           "from AsAuthTransactionButton b " +
           "join AsAuthTransaction t on b.AUTHTRANSACTIONGUID = t.AUTHTRANSACTIONGUID " +
           "join AsAuthPlan ap on t.AUTHPLANGUID = ap.AUTHPLANGUID " +
           "join AsAuthCompany c on ap.AUTHCOMPANYGUID = c.AUTHCOMPANYGUID " +
           "where c.SECURITYGROUPGUID = :securityGroupGuid")
    List<Projection> findBySecurityGroupGuid(@Param("securityGroupGuid") String securityGroupGuid);
}
