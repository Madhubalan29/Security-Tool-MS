package com.example.secondaryDev.repository;

import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.entity.security.AsAuthProductPage;

@Repository
public interface AsAuthProductPageRepository extends JpaRepository<AsAuthProductPage, String> {
    interface Projection {
        String getAUTHPRODUCTPAGEGUID();
        String getAUTHPRODUCTGUID();
        String getAUTHPAGEGUID();
    }
    List<AsAuthProductPage> findBySECURITYGROUPGUID(String securityGroupGuid);
    List<AsAuthProductPage> findBySECURITYGROUPGUIDAndCOMPANYGUIDAndPRODUCTGUID(String securityGroupGuid, String companyGuid, String productGuid);
    List<Projection> findByAUTHPRODUCTGUIDIn(Collection<String> authProductGuids);

    @Query("select p.AUTHPRODUCTPAGEGUID as AUTHPRODUCTPAGEGUID, p.AUTHPRODUCTGUID as AUTHPRODUCTGUID, p.AUTHPAGEGUID as AUTHPAGEGUID " +
           "from AsAuthProductPage p " +
           "join AsAuthProduct ap on p.AUTHPRODUCTGUID = ap.AUTHPRODUCTGUID " +
           "join AsAuthCompany c on ap.AUTHCOMPANYGUID = c.AUTHCOMPANYGUID " +
           "where c.SECURITYGROUPGUID = :securityGroupGuid")
    List<Projection> findBySecurityGroupGuid(@Param("securityGroupGuid") String securityGroupGuid);
}
