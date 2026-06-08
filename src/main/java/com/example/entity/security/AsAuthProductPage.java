package com.example.entity.security;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import org.hibernate.annotations.Formula;

@Entity
@Table(name = "ASAUTHPRODUCTPAGE")
public class AsAuthProductPage {

    @Id
    @Column(name = "AUTHPRODUCTPAGEGUID")
    private String AUTHPRODUCTPAGEGUID;

    @Column(name = "AUTHPRODUCTGUID")
    private String AUTHPRODUCTGUID;

    @Column(name = "AUTHPAGEGUID")
    private String AUTHPAGEGUID;

    @Formula("(SELECT ac.SECURITYGROUPGUID FROM ASAUTHCOMPANY ac JOIN ASAUTHPRODUCT ap ON ac.AUTHCOMPANYGUID = ap.AUTHCOMPANYGUID WHERE ap.AUTHPRODUCTGUID = AUTHPRODUCTGUID)")
    private String SECURITYGROUPGUID;

    @Formula("(SELECT ac.COMPANYGUID FROM ASAUTHCOMPANY ac JOIN ASAUTHPRODUCT ap ON ac.AUTHCOMPANYGUID = ap.AUTHCOMPANYGUID WHERE ap.AUTHPRODUCTGUID = AUTHPRODUCTGUID)")
    private String COMPANYGUID;

    @Formula("(SELECT ap.PRODUCTGUID FROM ASAUTHPRODUCT ap WHERE ap.AUTHPRODUCTGUID = AUTHPRODUCTGUID)")
    private String PRODUCTGUID;

    @Formula("(SELECT apg.PAGENAME FROM ASAUTHPAGE apg WHERE apg.AUTHPAGEGUID = AUTHPAGEGUID)")
    private String PAGENAME;

    public String getAUTHPRODUCTPAGEGUID() { return AUTHPRODUCTPAGEGUID; }
    public void setAUTHPRODUCTPAGEGUID(String v) { this.AUTHPRODUCTPAGEGUID = v; }
    public String getAUTHPRODUCTGUID() { return AUTHPRODUCTGUID; }
    public void setAUTHPRODUCTGUID(String v) { this.AUTHPRODUCTGUID = v; }
    public String getAUTHPAGEGUID() { return AUTHPAGEGUID; }
    public void setAUTHPAGEGUID(String v) { this.AUTHPAGEGUID = v; }

    public String getSECURITYGROUPGUID() { return SECURITYGROUPGUID; }
    public void setSECURITYGROUPGUID(String v) { this.SECURITYGROUPGUID = v; }
    public String getCOMPANYGUID() { return COMPANYGUID; }
    public void setCOMPANYGUID(String v) { this.COMPANYGUID = v; }
    public String getPRODUCTGUID() { return PRODUCTGUID; }
    public void setPRODUCTGUID(String v) { this.PRODUCTGUID = v; }
    public String getPAGENAME() { return PAGENAME; }
    public void setPAGENAME(String v) { this.PAGENAME = v; }
}
