package com.example.secondaryDev.repository;

import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.entity.security.AsAuthCompanyPageButton;

@Repository
public interface AsAuthCompanyPageButtonRepository extends JpaRepository<AsAuthCompanyPageButton, String> {
    interface Projection {
        String getAUTHCOMPANYPAGEGUID();
        String getAUTHBUTTONGUID();
    }
    
    @Query("select b.AUTHCOMPANYPAGEGUID as AUTHCOMPANYPAGEGUID, b.AUTHBUTTONGUID as AUTHBUTTONGUID " +
           "from AsAuthCompanyPageButton b " +
           "join AsAuthCompanyPage p on b.AUTHCOMPANYPAGEGUID = p.AUTHCOMPANYPAGEGUID " +
           "join AsAuthCompany c on p.AUTHCOMPANYGUID = c.AUTHCOMPANYGUID " +
           "where c.SECURITYGROUPGUID = :securityGroupGuid")
    List<Projection> findBySecurityGroupGuid(@Param("securityGroupGuid") String securityGroupGuid);
}
