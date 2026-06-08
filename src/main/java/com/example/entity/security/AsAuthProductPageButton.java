package com.example.entity.security;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Column;
import org.hibernate.annotations.Formula;

@Entity
@Table(name = "ASAUTHPRODUCTPAGEBUTTON")
@IdClass(AsAuthProductPageButtonId.class)
public class AsAuthProductPageButton {

    @Id
    @Column(name = "AUTHPRODUCTPAGEGUID")
    private String AUTHPRODUCTPAGEGUID;

    @Id
    @Column(name = "AUTHBUTTONGUID")
    private String AUTHBUTTONGUID;

    @Formula("(SELECT ac.SECURITYGROUPGUID FROM ASAUTHCOMPANY ac JOIN ASAUTHPRODUCT ap ON ac.AUTHCOMPANYGUID = ap.AUTHCOMPANYGUID JOIN ASAUTHPRODUCTPAGE app ON ap.AUTHPRODUCTGUID = app.AUTHPRODUCTGUID WHERE app.AUTHPRODUCTPAGEGUID = AUTHPRODUCTPAGEGUID)")
    private String SECURITYGROUPGUID;

    @Formula("(SELECT ac.COMPANYGUID FROM ASAUTHCOMPANY ac JOIN ASAUTHPRODUCT ap ON ac.AUTHCOMPANYGUID = ap.AUTHCOMPANYGUID JOIN ASAUTHPRODUCTPAGE app ON ap.AUTHPRODUCTGUID = app.AUTHPRODUCTGUID WHERE app.AUTHPRODUCTPAGEGUID = AUTHPRODUCTPAGEGUID)")
    private String COMPANYGUID;

    @Formula("(SELECT ap.PRODUCTGUID FROM ASAUTHPRODUCT ap JOIN ASAUTHPRODUCTPAGE app ON ap.AUTHPRODUCTGUID = app.AUTHPRODUCTGUID WHERE app.AUTHPRODUCTPAGEGUID = AUTHPRODUCTPAGEGUID)")
    private String PRODUCTGUID;

    @Formula("(SELECT apg.PAGENAME FROM ASAUTHPAGE apg JOIN ASAUTHPRODUCTPAGE app ON apg.AUTHPAGEGUID = app.AUTHPAGEGUID WHERE app.AUTHPRODUCTPAGEGUID = AUTHPRODUCTPAGEGUID)")
    private String PAGENAME;

    @Formula("(SELECT ab.BUTTONNAME FROM ASAUTHBUTTON ab WHERE ab.AUTHBUTTONGUID = AUTHBUTTONGUID)")
    private String BUTTONNAME;

    public String getAUTHPRODUCTPAGEGUID() { return AUTHPRODUCTPAGEGUID; }
    public void setAUTHPRODUCTPAGEGUID(String v) { this.AUTHPRODUCTPAGEGUID = v; }
    public String getAUTHBUTTONGUID() { return AUTHBUTTONGUID; }
    public void setAUTHBUTTONGUID(String v) { this.AUTHBUTTONGUID = v; }

    public String getSECURITYGROUPGUID() { return SECURITYGROUPGUID; }
    public void setSECURITYGROUPGUID(String v) { this.SECURITYGROUPGUID = v; }
    public String getCOMPANYGUID() { return COMPANYGUID; }
    public void setCOMPANYGUID(String v) { this.COMPANYGUID = v; }
    public String getPRODUCTGUID() { return PRODUCTGUID; }
    public void setPRODUCTGUID(String v) { this.PRODUCTGUID = v; }
    public String getPAGENAME() { return PAGENAME; }
    public void setPAGENAME(String v) { this.PAGENAME = v; }
    public String getBUTTONNAME() { return BUTTONNAME; }
    public void setBUTTONNAME(String v) { this.BUTTONNAME = v; }
}
