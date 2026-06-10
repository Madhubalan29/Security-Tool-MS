package com.example.secondaryDev.repository;

import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.entity.security.AsAuthPlanInquiry;

@Repository
public interface AsAuthPlanInquiryRepository extends JpaRepository<AsAuthPlanInquiry, String> {
    interface Projection {
        String getAUTHPLANINQUIRYGUID();
        String getAUTHPLANGUID();
        String getINQUIRYSCREENGUID();
    }
    List<AsAuthPlanInquiry> findBySECURITYGROUPGUID(String securityGroupGuid);
    List<AsAuthPlanInquiry> findBySECURITYGROUPGUIDAndCOMPANYGUIDAndPLANGUID(String securityGroupGuid, String companyGuid, String planGuid);
    List<Projection> findByAUTHPLANGUIDIn(Collection<String> authPlanGuids);

    @Query("select i.AUTHPLANINQUIRYGUID as AUTHPLANINQUIRYGUID, i.AUTHPLANGUID as AUTHPLANGUID, i.INQUIRYSCREENGUID as INQUIRYSCREENGUID " +
           "from AsAuthPlanInquiry i " +
           "join AsAuthPlan ap on i.AUTHPLANGUID = ap.AUTHPLANGUID " +
           "join AsAuthCompany c on ap.AUTHCOMPANYGUID = c.AUTHCOMPANYGUID " +
           "where c.SECURITYGROUPGUID = :securityGroupGuid")
    List<Projection> findBySecurityGroupGuid(@Param("securityGroupGuid") String securityGroupGuid);
}
