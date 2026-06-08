package com.example.entity.security;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Column;
import org.hibernate.annotations.Formula;

@Entity
@Table(name = "ASAUTHPRODUCTTRANSACTIONBUTTON")
@IdClass(AsAuthProductTransactionButtonId.class)
public class AsAuthProductTransactionButton {

    @Id
    @Column(name = "AUTHPRODUCTTRANSACTIONGUID")
    private String AUTHPRODUCTTRANSACTIONGUID;

    @Id
    @Column(name = "AUTHBUTTONGUID")
    private String AUTHBUTTONGUID;

    @Formula("(SELECT ac.SECURITYGROUPGUID FROM ASAUTHCOMPANY ac JOIN ASAUTHPRODUCT ap ON ac.AUTHCOMPANYGUID = ap.AUTHCOMPANYGUID JOIN ASAUTHPRODUCTTRANSACTION apt ON ap.AUTHPRODUCTGUID = apt.AUTHPRODUCTGUID WHERE apt.AUTHPRODUCTTRANSACTIONGUID = AUTHPRODUCTTRANSACTIONGUID)")
    private String SECURITYGROUPGUID;

    public String getAUTHPRODUCTTRANSACTIONGUID() {
        return AUTHPRODUCTTRANSACTIONGUID;
    }

    public void setAUTHPRODUCTTRANSACTIONGUID(String AUTHPRODUCTTRANSACTIONGUID) {
        this.AUTHPRODUCTTRANSACTIONGUID = AUTHPRODUCTTRANSACTIONGUID;
    }

    public String getAUTHBUTTONGUID() {
        return AUTHBUTTONGUID;
    }

    public void setAUTHBUTTONGUID(String AUTHBUTTONGUID) {
        this.AUTHBUTTONGUID = AUTHBUTTONGUID;
    }

    public String getSECURITYGROUPGUID() {
        return SECURITYGROUPGUID;
    }

    public void setSECURITYGROUPGUID(String SECURITYGROUPGUID) {
        this.SECURITYGROUPGUID = SECURITYGROUPGUID;
    }
}
