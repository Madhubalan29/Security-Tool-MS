package com.example.secondaryDev.repository;

import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.entity.security.AsAuthCompanyWebService;

@Repository
public interface AsAuthCompanyWebServiceRepository extends JpaRepository<AsAuthCompanyWebService, String> {
    interface Projection {
        String getAUTHCOMPANYGUID();
        String getAUTHWEBSERVICEGUID();
    }
    List<AsAuthCompanyWebService> findBySECURITYGROUPGUID(String securityGroupGuid);
    List<AsAuthCompanyWebService> findBySECURITYGROUPGUIDAndCOMPANYGUID(String securityGroupGuid, String companyGuid);
    List<Projection> findByAUTHCOMPANYGUIDIn(Collection<String> authCompanyGuids);

    @Query("select w.AUTHWEBSERVICEGUID as AUTHWEBSERVICEGUID, w.AUTHCOMPANYGUID as AUTHCOMPANYGUID " +
           "from AsAuthCompanyWebService w " +
           "join AsAuthCompany c on w.AUTHCOMPANYGUID = c.AUTHCOMPANYGUID " +
           "where c.SECURITYGROUPGUID = :securityGroupGuid")
    List<Projection> findBySecurityGroupGuid(@Param("securityGroupGuid") String securityGroupGuid);
}
