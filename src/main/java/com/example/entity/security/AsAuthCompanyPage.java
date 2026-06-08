package com.example.entity.security;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import org.hibernate.annotations.Formula;

@Entity
@Table(name = "ASAUTHCOMPANYPAGE")
public class AsAuthCompanyPage {

    @Id
    @Column(name = "AUTHCOMPANYPAGEGUID")
    private String AUTHCOMPANYPAGEGUID;

    @Column(name = "AUTHCOMPANYGUID")
    private String AUTHCOMPANYGUID;

    @Column(name = "AUTHPAGEGUID")
    private String AUTHPAGEGUID;

    @Formula("(SELECT ac.SECURITYGROUPGUID FROM ASAUTHCOMPANY ac WHERE ac.AUTHCOMPANYGUID = AUTHCOMPANYGUID)")
    private String SECURITYGROUPGUID;

    @Formula("(SELECT ac.COMPANYGUID FROM ASAUTHCOMPANY ac WHERE ac.AUTHCOMPANYGUID = AUTHCOMPANYGUID)")
    private String COMPANYGUID;

    @Formula("(SELECT ap.PAGENAME FROM ASAUTHPAGE ap WHERE ap.AUTHPAGEGUID = AUTHPAGEGUID)")
    private String PAGENAME;

    public String getAUTHCOMPANYPAGEGUID() { return AUTHCOMPANYPAGEGUID; }
    public void setAUTHCOMPANYPAGEGUID(String v) { this.AUTHCOMPANYPAGEGUID = v; }
    public String getAUTHCOMPANYGUID() { return AUTHCOMPANYGUID; }
    public void setAUTHCOMPANYGUID(String v) { this.AUTHCOMPANYGUID = v; }
    public String getAUTHPAGEGUID() { return AUTHPAGEGUID; }
    public void setAUTHPAGEGUID(String v) { this.AUTHPAGEGUID = v; }

    public String getSECURITYGROUPGUID() { return SECURITYGROUPGUID; }
    public void setSECURITYGROUPGUID(String v) { this.SECURITYGROUPGUID = v; }
    public String getCOMPANYGUID() { return COMPANYGUID; }
    public void setCOMPANYGUID(String v) { this.COMPANYGUID = v; }
    public String getPAGENAME() { return PAGENAME; }
    public void setPAGENAME(String v) { this.PAGENAME = v; }
}
