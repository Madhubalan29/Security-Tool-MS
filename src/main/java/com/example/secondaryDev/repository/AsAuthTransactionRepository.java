package com.example.secondaryDev.repository;

import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.entity.security.AsAuthTransaction;

@Repository
public interface AsAuthTransactionRepository extends JpaRepository<AsAuthTransaction, String> {
    interface Projection {
        String getAUTHTRANSACTIONGUID();
        String getAUTHPLANGUID();
        String getTRANSACTIONGUID();
    }
    List<AsAuthTransaction> findBySECURITYGROUPGUID(String securityGroupGuid);
    List<AsAuthTransaction> findBySECURITYGROUPGUIDAndCOMPANYGUIDAndPLANGUID(String securityGroupGuid, String companyGuid, String planGuid);
    List<Projection> findByAUTHPLANGUIDIn(Collection<String> authPlanGuids);

    @Query("select t.AUTHTRANSACTIONGUID as AUTHTRANSACTIONGUID, t.AUTHPLANGUID as AUTHPLANGUID, t.TRANSACTIONGUID as TRANSACTIONGUID " +
           "from AsAuthTransaction t " +
           "join AsAuthPlan ap on t.AUTHPLANGUID = ap.AUTHPLANGUID " +
           "join AsAuthCompany c on ap.AUTHCOMPANYGUID = c.AUTHCOMPANYGUID " +
           "where c.SECURITYGROUPGUID = :securityGroupGuid")
    List<Projection> findBySecurityGroupGuid(@Param("securityGroupGuid") String securityGroupGuid);
}
