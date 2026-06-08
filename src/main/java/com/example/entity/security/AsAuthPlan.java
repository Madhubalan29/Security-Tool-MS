package com.example.entity.security;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import org.hibernate.annotations.Formula;

@Entity
@Table(name = "ASAUTHPLAN")
public class AsAuthPlan {

    @Id
    @Column(name = "AUTHPLANGUID")
    private String AUTHPLANGUID;

    @Column(name = "AUTHCOMPANYGUID")
    private String AUTHCOMPANYGUID;

    @Column(name = "PLANGUID")
    private String PLANGUID;

    @Formula("(SELECT ac.SECURITYGROUPGUID FROM ASAUTHCOMPANY ac WHERE ac.AUTHCOMPANYGUID = AUTHCOMPANYGUID)")
    private String SECURITYGROUPGUID;

    @Formula("(SELECT ac.COMPANYGUID FROM ASAUTHCOMPANY ac WHERE ac.AUTHCOMPANYGUID = AUTHCOMPANYGUID)")
    private String COMPANYGUID;

    public String getAUTHPLANGUID() { return AUTHPLANGUID; }
    public void setAUTHPLANGUID(String v) { this.AUTHPLANGUID = v; }
    public String getAUTHCOMPANYGUID() { return AUTHCOMPANYGUID; }
    public void setAUTHCOMPANYGUID(String v) { this.AUTHCOMPANYGUID = v; }
    public String getPLANGUID() { return PLANGUID; }
    public void setPLANGUID(String v) { this.PLANGUID = v; }

    public String getSECURITYGROUPGUID() { return SECURITYGROUPGUID; }
    public void setSECURITYGROUPGUID(String v) { this.SECURITYGROUPGUID = v; }
    public String getCOMPANYGUID() { return COMPANYGUID; }
    public void setCOMPANYGUID(String v) { this.COMPANYGUID = v; }
}
