package com.example.entity.security;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import org.hibernate.annotations.Formula;

@Entity
@Table(name = "ASAUTHPRODUCT")
public class AsAuthProduct {

    @Id
    @Column(name = "AUTHPRODUCTGUID")
    private String AUTHPRODUCTGUID;

    @Column(name = "AUTHCOMPANYGUID")
    private String AUTHCOMPANYGUID;

    @Column(name = "PRODUCTGUID")
    private String PRODUCTGUID;

    @Formula("(SELECT ac.SECURITYGROUPGUID FROM ASAUTHCOMPANY ac WHERE ac.AUTHCOMPANYGUID = AUTHCOMPANYGUID)")
    private String SECURITYGROUPGUID;

    @Formula("(SELECT ac.COMPANYGUID FROM ASAUTHCOMPANY ac WHERE ac.AUTHCOMPANYGUID = AUTHCOMPANYGUID)")
    private String COMPANYGUID;

    public String getAUTHPRODUCTGUID() { return AUTHPRODUCTGUID; }
    public void setAUTHPRODUCTGUID(String v) { this.AUTHPRODUCTGUID = v; }
    public String getAUTHCOMPANYGUID() { return AUTHCOMPANYGUID; }
    public void setAUTHCOMPANYGUID(String v) { this.AUTHCOMPANYGUID = v; }
    public String getPRODUCTGUID() { return PRODUCTGUID; }
    public void setPRODUCTGUID(String v) { this.PRODUCTGUID = v; }

    public String getSECURITYGROUPGUID() { return SECURITYGROUPGUID; }
    public void setSECURITYGROUPGUID(String v) { this.SECURITYGROUPGUID = v; }
    public String getCOMPANYGUID() { return COMPANYGUID; }
    public void setCOMPANYGUID(String v) { this.COMPANYGUID = v; }
}
