package com.example.entity.security;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import org.hibernate.annotations.Formula;

@Entity
@Table(name = "ASAUTHCOMPANYINQUIRY")
public class AsAuthCompanyInquiry {

    @Id
    @Column(name = "AUTHCOMPANYINQUIRYGUID")
    private String AUTHCOMPANYINQUIRYGUID;

    @Column(name = "AUTHCOMPANYGUID")
    private String AUTHCOMPANYGUID;

    @Column(name = "INQUIRYSCREENNAMEGUID")
    private String INQUIRYSCREENGUID;

    @Formula("(SELECT ac.SECURITYGROUPGUID FROM ASAUTHCOMPANY ac WHERE ac.AUTHCOMPANYGUID = AUTHCOMPANYGUID)")
    private String SECURITYGROUPGUID;

    @Formula("(SELECT ac.COMPANYGUID FROM ASAUTHCOMPANY ac WHERE ac.AUTHCOMPANYGUID = AUTHCOMPANYGUID)")
    private String COMPANYGUID;

    @Formula("(SELECT i.SCREENNAME FROM ASINQUIRYSCREEN i WHERE i.INQUIRYSCREENGUID = INQUIRYSCREENNAMEGUID)")
    private String INQUIRYNAME;

    public String getAUTHCOMPANYINQUIRYGUID() { return AUTHCOMPANYINQUIRYGUID; }
    public void setAUTHCOMPANYINQUIRYGUID(String v) { this.AUTHCOMPANYINQUIRYGUID = v; }
    public String getAUTHCOMPANYGUID() { return AUTHCOMPANYGUID; }
    public void setAUTHCOMPANYGUID(String v) { this.AUTHCOMPANYGUID = v; }
    public String getINQUIRYSCREENGUID() { return INQUIRYSCREENGUID; }
    public void setINQUIRYSCREENGUID(String v) { this.INQUIRYSCREENGUID = v; }

    public String getSECURITYGROUPGUID() { return SECURITYGROUPGUID; }
    public void setSECURITYGROUPGUID(String v) { this.SECURITYGROUPGUID = v; }
    public String getCOMPANYGUID() { return COMPANYGUID; }
    public void setCOMPANYGUID(String v) { this.COMPANYGUID = v; }
    public String getINQUIRYNAME() { return INQUIRYNAME; }
    public void setINQUIRYNAME(String v) { this.INQUIRYNAME = v; }
}
