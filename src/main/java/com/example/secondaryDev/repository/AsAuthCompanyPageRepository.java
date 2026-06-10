package com.example.secondaryDev.repository;

import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.entity.security.AsAuthCompanyPage;

@Repository
public interface AsAuthCompanyPageRepository extends JpaRepository<AsAuthCompanyPage, String> {
    interface Projection {
        String getAUTHCOMPANYPAGEGUID();
        String getAUTHCOMPANYGUID();
        String getAUTHPAGEGUID();
    }
    List<AsAuthCompanyPage> findBySECURITYGROUPGUID(String securityGroupGuid);
    List<AsAuthCompanyPage> findBySECURITYGROUPGUIDAndCOMPANYGUID(String securityGroupGuid, String companyGuid);
    List<Projection> findByAUTHCOMPANYGUIDIn(Collection<String> authCompanyGuids);

    @Query("select p.AUTHCOMPANYPAGEGUID as AUTHCOMPANYPAGEGUID, p.AUTHCOMPANYGUID as AUTHCOMPANYGUID, p.AUTHPAGEGUID as AUTHPAGEGUID " +
           "from AsAuthCompanyPage p " +
           "join AsAuthCompany c on p.AUTHCOMPANYGUID = c.AUTHCOMPANYGUID " +
           "where c.SECURITYGROUPGUID = :securityGroupGuid")
    List<Projection> findBySecurityGroupGuid(@Param("securityGroupGuid") String securityGroupGuid);
}
