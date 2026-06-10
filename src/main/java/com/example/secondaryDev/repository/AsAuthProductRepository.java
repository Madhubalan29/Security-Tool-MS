package com.example.secondaryDev.repository;

import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.entity.security.AsAuthProduct;

@Repository
public interface AsAuthProductRepository extends JpaRepository<AsAuthProduct, String> {
    interface Projection {
        String getAUTHPRODUCTGUID();
        String getAUTHCOMPANYGUID();
        String getPRODUCTGUID();
    }
    List<AsAuthProduct> findBySECURITYGROUPGUID(String securityGroupGuid);
    List<AsAuthProduct> findBySECURITYGROUPGUIDAndCOMPANYGUID(String securityGroupGuid, String companyGuid);
    List<Projection> findByAUTHCOMPANYGUIDIn(Collection<String> authCompanyGuids);

    @Query("select p.AUTHPRODUCTGUID as AUTHPRODUCTGUID, p.AUTHCOMPANYGUID as AUTHCOMPANYGUID, p.PRODUCTGUID as PRODUCTGUID " +
           "from AsAuthProduct p " +
           "join AsAuthCompany c on p.AUTHCOMPANYGUID = c.AUTHCOMPANYGUID " +
           "where c.SECURITYGROUPGUID = :securityGroupGuid")
    List<Projection> findBySecurityGroupGuid(@Param("securityGroupGuid") String securityGroupGuid);
}
