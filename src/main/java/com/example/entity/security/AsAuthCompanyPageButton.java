package com.example.entity.security;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import org.hibernate.annotations.Formula;

@Entity
@Table(name = "ASAUTHCOMPANYPAGEBUTTON")
public class AsAuthCompanyPageButton {

    @Id
    @Column(name = "AUTHCOMPANYPAGEBUTTONGUID")
    private String AUTHCOMPANYPAGEBUTTONGUID;

    @Column(name = "AUTHCOMPANYPAGEGUID")
    private String AUTHCOMPANYPAGEGUID;

    @Column(name = "AUTHBUTTONGUID")
    private String AUTHBUTTONGUID;

    @Formula("(SELECT ac.SECURITYGROUPGUID FROM ASAUTHCOMPANY ac JOIN ASAUTHCOMPANYPAGE acp ON ac.AUTHCOMPANYGUID = acp.AUTHCOMPANYGUID WHERE acp.AUTHCOMPANYPAGEGUID = AUTHCOMPANYPAGEGUID)")
    private String SECURITYGROUPGUID;

    @Formula("(SELECT ac.COMPANYGUID FROM ASAUTHCOMPANY ac JOIN ASAUTHCOMPANYPAGE acp ON ac.AUTHCOMPANYGUID = acp.AUTHCOMPANYGUID WHERE acp.AUTHCOMPANYPAGEGUID = AUTHCOMPANYPAGEGUID)")
    private String COMPANYGUID;

    @Formula("(SELECT ap.PAGENAME FROM ASAUTHPAGE ap JOIN ASAUTHCOMPANYPAGE acp ON ap.AUTHPAGEGUID = acp.AUTHPAGEGUID WHERE acp.AUTHCOMPANYPAGEGUID = AUTHCOMPANYPAGEGUID)")
    private String PAGENAME;

    @Formula("(SELECT ab.BUTTONNAME FROM ASAUTHBUTTON ab WHERE ab.AUTHBUTTONGUID = AUTHBUTTONGUID)")
    private String BUTTONNAME;

    public String getAUTHCOMPANYPAGEBUTTONGUID() { return AUTHCOMPANYPAGEBUTTONGUID; }
    public void setAUTHCOMPANYPAGEBUTTONGUID(String v) { this.AUTHCOMPANYPAGEBUTTONGUID = v; }
    public String getAUTHCOMPANYPAGEGUID() { return AUTHCOMPANYPAGEGUID; }
    public void setAUTHCOMPANYPAGEGUID(String v) { this.AUTHCOMPANYPAGEGUID = v; }
    public String getAUTHBUTTONGUID() { return AUTHBUTTONGUID; }
    public void setAUTHBUTTONGUID(String v) { this.AUTHBUTTONGUID = v; }

    public String getSECURITYGROUPGUID() { return SECURITYGROUPGUID; }
    public void setSECURITYGROUPGUID(String v) { this.SECURITYGROUPGUID = v; }
    public String getCOMPANYGUID() { return COMPANYGUID; }
    public void setCOMPANYGUID(String v) { this.COMPANYGUID = v; }
    public String getPAGENAME() { return PAGENAME; }
    public void setPAGENAME(String v) { this.PAGENAME = v; }
    public String getBUTTONNAME() { return BUTTONNAME; }
    public void setBUTTONNAME(String v) { this.BUTTONNAME = v; }
}
