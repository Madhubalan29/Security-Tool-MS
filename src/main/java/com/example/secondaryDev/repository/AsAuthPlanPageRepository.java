package com.example.secondaryDev.repository;

import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.entity.security.AsAuthPlanPage;

@Repository
public interface AsAuthPlanPageRepository extends JpaRepository<AsAuthPlanPage, String> {
    interface Projection {
        String getAUTHPLANPAGEGUID();
        String getAUTHPLANGUID();
        String getAUTHPAGEGUID();
    }
    List<AsAuthPlanPage> findBySECURITYGROUPGUID(String securityGroupGuid);
    List<AsAuthPlanPage> findBySECURITYGROUPGUIDAndCOMPANYGUIDAndPLANGUID(String securityGroupGuid, String companyGuid, String planGuid);
    List<Projection> findByAUTHPLANGUIDIn(Collection<String> authPlanGuids);

    @Query("select p.AUTHPLANPAGEGUID as AUTHPLANPAGEGUID, p.AUTHPLANGUID as AUTHPLANGUID, p.AUTHPAGEGUID as AUTHPAGEGUID " +
           "from AsAuthPlanPage p " +
           "join AsAuthPlan ap on p.AUTHPLANGUID = ap.AUTHPLANGUID " +
           "join AsAuthCompany c on ap.AUTHCOMPANYGUID = c.AUTHCOMPANYGUID " +
           "where c.SECURITYGROUPGUID = :securityGroupGuid")
    List<Projection> findBySecurityGroupGuid(@Param("securityGroupGuid") String securityGroupGuid);
}
