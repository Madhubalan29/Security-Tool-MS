package com.example.secondaryDev.repository;

import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.entity.security.AsAuthPlan;

@Repository
public interface AsAuthPlanRepository extends JpaRepository<AsAuthPlan, String> {
    interface Projection {
        String getAUTHPLANGUID();
        String getAUTHCOMPANYGUID();
        String getPLANGUID();
    }
    List<AsAuthPlan> findBySECURITYGROUPGUID(String securityGroupGuid);
    List<AsAuthPlan> findBySECURITYGROUPGUIDAndCOMPANYGUID(String securityGroupGuid, String companyGuid);
    List<Projection> findByAUTHCOMPANYGUIDIn(Collection<String> authCompanyGuids);

    @Query("select p.AUTHPLANGUID as AUTHPLANGUID, p.AUTHCOMPANYGUID as AUTHCOMPANYGUID, p.PLANGUID as PLANGUID " +
           "from AsAuthPlan p " +
           "join AsAuthCompany c on p.AUTHCOMPANYGUID = c.AUTHCOMPANYGUID " +
           "where c.SECURITYGROUPGUID = :securityGroupGuid")
    List<Projection> findBySecurityGroupGuid(@Param("securityGroupGuid") String securityGroupGuid);
}
