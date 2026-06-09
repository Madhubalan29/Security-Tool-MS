package com.example.secondaryDev.repository;

import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.entity.security.AsAuthProductTransaction;

@Repository
public interface AsAuthProductTransactionRepository extends JpaRepository<AsAuthProductTransaction, String> {
    interface Projection {
        String getAUTHPRODUCTTRANSACTIONGUID();
        String getAUTHPRODUCTGUID();
        String getTRANSACTIONGUID();
    }
    List<AsAuthProductTransaction> findBySECURITYGROUPGUID(String securityGroupGuid);
    List<AsAuthProductTransaction> findBySECURITYGROUPGUIDAndCOMPANYGUIDAndPRODUCTGUID(String securityGroupGuid, String companyGuid, String productGuid);
    List<Projection> findByAUTHPRODUCTGUIDIn(Collection<String> authProductGuids);

    @Query("select t.AUTHPRODUCTTRANSACTIONGUID as AUTHPRODUCTTRANSACTIONGUID, t.AUTHPRODUCTGUID as AUTHPRODUCTGUID, t.TRANSACTIONGUID as TRANSACTIONGUID " +
           "from AsAuthProductTransaction t " +
           "join AsAuthProduct ap on t.AUTHPRODUCTGUID = ap.AUTHPRODUCTGUID " +
           "join AsAuthCompany c on ap.AUTHCOMPANYGUID = c.AUTHCOMPANYGUID " +
           "where c.SECURITYGROUPGUID = :securityGroupGuid")
    List<Projection> findBySecurityGroupGuid(@Param("securityGroupGuid") String securityGroupGuid);
}
