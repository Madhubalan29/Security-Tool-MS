package com.example.secondaryDev.repository;

import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.entity.security.AsAuthPlanPageButton;

@Repository
public interface AsAuthPlanPageButtonRepository extends JpaRepository<AsAuthPlanPageButton, String> {
    interface Projection {
        String getAUTHPLANPAGEGUID();
        String getAUTHBUTTONGUID();
    }
    @Query("select b.AUTHPLANPAGEGUID as AUTHPLANPAGEGUID, b.AUTHBUTTONGUID as AUTHBUTTONGUID " +
           "from AsAuthPlanPageButton b " +
           "join AsAuthPlanPage p on b.AUTHPLANPAGEGUID = p.AUTHPLANPAGEGUID " +
           "join AsAuthPlan ap on p.AUTHPLANGUID = ap.AUTHPLANGUID " +
           "join AsAuthCompany c on ap.AUTHCOMPANYGUID = c.AUTHCOMPANYGUID " +
           "where c.SECURITYGROUPGUID = :securityGroupGuid")
    List<Projection> findBySecurityGroupGuid(@Param("securityGroupGuid") String securityGroupGuid);
}
