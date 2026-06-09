package com.example.secondaryDev.repository;

import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.entity.security.AsAuthCompanyInquiry;

@Repository
public interface AsAuthCompanyInquiryRepository extends JpaRepository<AsAuthCompanyInquiry, String> {
    interface Projection {
        String getAUTHCOMPANYINQUIRYGUID();
        String getAUTHCOMPANYGUID();
        String getINQUIRYSCREENGUID();
    }
    List<AsAuthCompanyInquiry> findBySECURITYGROUPGUID(String securityGroupGuid);
    List<AsAuthCompanyInquiry> findBySECURITYGROUPGUIDAndCOMPANYGUID(String securityGroupGuid, String companyGuid);
    List<Projection> findByAUTHCOMPANYGUIDIn(Collection<String> authCompanyGuids);

    @Query("select i.AUTHCOMPANYINQUIRYGUID as AUTHCOMPANYINQUIRYGUID, i.AUTHCOMPANYGUID as AUTHCOMPANYGUID, i.INQUIRYSCREENGUID as INQUIRYSCREENGUID " +
           "from AsAuthCompanyInquiry i " +
           "join AsAuthCompany c on i.AUTHCOMPANYGUID = c.AUTHCOMPANYGUID " +
           "where c.SECURITYGROUPGUID = :securityGroupGuid")
    List<Projection> findBySecurityGroupGuid(@Param("securityGroupGuid") String securityGroupGuid);
}
