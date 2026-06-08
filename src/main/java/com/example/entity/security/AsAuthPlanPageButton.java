package com.example.entity.security;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Column;
import org.hibernate.annotations.Formula;

@Entity
@Table(name = "ASAUTHPLANPAGEBUTTON")
@IdClass(AsAuthPlanPageButtonId.class)
public class AsAuthPlanPageButton {

    @Id
    @Column(name = "AUTHPLANPAGEGUID")
    private String AUTHPLANPAGEGUID;

    @Id
    @Column(name = "AUTHBUTTONGUID")
    private String AUTHBUTTONGUID;

    @Formula("(SELECT ac.SECURITYGROUPGUID FROM ASAUTHCOMPANY ac JOIN ASAUTHPLAN ap ON ac.AUTHCOMPANYGUID = ap.AUTHCOMPANYGUID JOIN ASAUTHPLANPAGE app ON ap.AUTHPLANGUID = app.AUTHPLANGUID WHERE app.AUTHPLANPAGEGUID = AUTHPLANPAGEGUID)")
    private String SECURITYGROUPGUID;

    @Formula("(SELECT ac.COMPANYGUID FROM ASAUTHCOMPANY ac JOIN ASAUTHPLAN ap ON ac.AUTHCOMPANYGUID = ap.AUTHCOMPANYGUID JOIN ASAUTHPLANPAGE app ON ap.AUTHPLANGUID = app.AUTHPLANGUID WHERE app.AUTHPLANPAGEGUID = AUTHPLANPAGEGUID)")
    private String COMPANYGUID;

    @Formula("(SELECT ap.PLANGUID FROM ASAUTHPLAN ap JOIN ASAUTHPLANPAGE app ON ap.AUTHPLANGUID = app.AUTHPLANGUID WHERE app.AUTHPLANPAGEGUID = AUTHPLANPAGEGUID)")
    private String PLANGUID;

    @Formula("(SELECT apg.PAGENAME FROM ASAUTHPAGE apg JOIN ASAUTHPLANPAGE app ON apg.AUTHPAGEGUID = app.AUTHPAGEGUID WHERE app.AUTHPLANPAGEGUID = AUTHPLANPAGEGUID)")
    private String PAGENAME;

    @Formula("(SELECT ab.BUTTONNAME FROM ASAUTHBUTTON ab WHERE ab.AUTHBUTTONGUID = AUTHBUTTONGUID)")
    private String BUTTONNAME;

    public String getAUTHPLANPAGEGUID() { return AUTHPLANPAGEGUID; }
    public void setAUTHPLANPAGEGUID(String v) { this.AUTHPLANPAGEGUID = v; }
    public String getAUTHBUTTONGUID() { return AUTHBUTTONGUID; }
    public void setAUTHBUTTONGUID(String v) { this.AUTHBUTTONGUID = v; }

    public String getSECURITYGROUPGUID() { return SECURITYGROUPGUID; }
    public void setSECURITYGROUPGUID(String v) { this.SECURITYGROUPGUID = v; }
    public String getCOMPANYGUID() { return COMPANYGUID; }
    public void setCOMPANYGUID(String v) { this.COMPANYGUID = v; }
    public String getPLANGUID() { return PLANGUID; }
    public void setPLANGUID(String v) { this.PLANGUID = v; }
    public String getPAGENAME() { return PAGENAME; }
    public void setPAGENAME(String v) { this.PAGENAME = v; }
    public String getBUTTONNAME() { return BUTTONNAME; }
    public void setBUTTONNAME(String v) { this.BUTTONNAME = v; }
}
