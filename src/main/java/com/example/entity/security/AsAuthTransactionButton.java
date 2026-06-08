package com.example.entity.security;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Column;
import org.hibernate.annotations.Formula;

@Entity
@Table(name = "ASAUTHTRANSACTIONBUTTON")
@IdClass(AsAuthTransactionButtonId.class)
public class AsAuthTransactionButton {

    @Id
    @Column(name = "AUTHTRANSACTIONGUID")
    private String AUTHTRANSACTIONGUID;

    @Id
    @Column(name = "AUTHBUTTONGUID")
    private String AUTHBUTTONGUID;

    @Formula("(SELECT ac.SECURITYGROUPGUID FROM ASAUTHCOMPANY ac JOIN ASAUTHPLAN ap ON ac.AUTHCOMPANYGUID = ap.AUTHCOMPANYGUID JOIN ASAUTHTRANSACTION at ON ap.AUTHPLANGUID = at.AUTHPLANGUID WHERE at.AUTHTRANSACTIONGUID = AUTHTRANSACTIONGUID)")
    private String SECURITYGROUPGUID;

    public String getAUTHTRANSACTIONGUID() {
        return AUTHTRANSACTIONGUID;
    }

    public void setAUTHTRANSACTIONGUID(String AUTHTRANSACTIONGUID) {
        this.AUTHTRANSACTIONGUID = AUTHTRANSACTIONGUID;
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
