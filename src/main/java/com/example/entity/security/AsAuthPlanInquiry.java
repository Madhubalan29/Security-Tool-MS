package com.example.entity.security;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import org.hibernate.annotations.Formula;

@Entity
@Table(name = "ASAUTHPLANINQUIRY")
public class AsAuthPlanInquiry {

    @Id
    @Column(name = "AUTHPLANINQUIRYGUID")
    private String AUTHPLANINQUIRYGUID;

    @Column(name = "AUTHPLANGUID")
    private String AUTHPLANGUID;

    @Column(name = "INQUIRYSCREENNAMEGUID")
    private String INQUIRYSCREENGUID;

    @Formula("(SELECT ac.SECURITYGROUPGUID FROM ASAUTHCOMPANY ac JOIN ASAUTHPLAN ap ON ac.AUTHCOMPANYGUID = ap.AUTHCOMPANYGUID WHERE ap.AUTHPLANGUID = AUTHPLANGUID)")
    private String SECURITYGROUPGUID;

    @Formula("(SELECT ac.COMPANYGUID FROM ASAUTHCOMPANY ac JOIN ASAUTHPLAN ap ON ac.AUTHCOMPANYGUID = ap.AUTHCOMPANYGUID WHERE ap.AUTHPLANGUID = AUTHPLANGUID)")
    private String COMPANYGUID;

    @Formula("(SELECT ap.PLANGUID FROM ASAUTHPLAN ap WHERE ap.AUTHPLANGUID = AUTHPLANGUID)")
    private String PLANGUID;

    @Formula("(SELECT i.SCREENNAME FROM ASINQUIRYSCREEN i WHERE i.INQUIRYSCREENGUID = INQUIRYSCREENNAMEGUID)")
    private String INQUIRYNAME;

    public String getAUTHPLANINQUIRYGUID() { return AUTHPLANINQUIRYGUID; }
    public void setAUTHPLANINQUIRYGUID(String v) { this.AUTHPLANINQUIRYGUID = v; }
    public String getAUTHPLANGUID() { return AUTHPLANGUID; }
    public void setAUTHPLANGUID(String v) { this.AUTHPLANGUID = v; }
    public String getINQUIRYSCREENGUID() { return INQUIRYSCREENGUID; }
    public void setINQUIRYSCREENGUID(String v) { this.INQUIRYSCREENGUID = v; }

    public String getSECURITYGROUPGUID() { return SECURITYGROUPGUID; }
    public void setSECURITYGROUPGUID(String v) { this.SECURITYGROUPGUID = v; }
    public String getCOMPANYGUID() { return COMPANYGUID; }
    public void setCOMPANYGUID(String v) { this.COMPANYGUID = v; }
    public String getPLANGUID() { return PLANGUID; }
    public void setPLANGUID(String v) { this.PLANGUID = v; }
    public String getINQUIRYNAME() { return INQUIRYNAME; }
    public void setINQUIRYNAME(String v) { this.INQUIRYNAME = v; }
}
