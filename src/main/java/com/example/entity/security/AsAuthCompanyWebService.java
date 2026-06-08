package com.example.entity.security;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Column;
import org.hibernate.annotations.Formula;

@Entity
@Table(name = "ASAUTHCOMPANYWEBSERVICE")
@IdClass(AsAuthCompanyWebServiceId.class)
public class AsAuthCompanyWebService {

    @Id
    @Column(name = "AUTHCOMPANYGUID")
    private String AUTHCOMPANYGUID;

    @Id
    @Column(name = "AUTHWEBSERVICEGUID")
    private String AUTHWEBSERVICEGUID;

    @Formula("(SELECT ac.SECURITYGROUPGUID FROM ASAUTHCOMPANY ac WHERE ac.AUTHCOMPANYGUID = AUTHCOMPANYGUID)")
    private String SECURITYGROUPGUID;

    @Formula("(SELECT ac.COMPANYGUID FROM ASAUTHCOMPANY ac WHERE ac.AUTHCOMPANYGUID = AUTHCOMPANYGUID)")
    private String COMPANYGUID;

    @Formula("(SELECT aw.WEBSERVICENAME FROM ASAUTHWEBSERVICE aw WHERE aw.AUTHWEBSERVICEGUID = AUTHWEBSERVICEGUID)")
    private String WEBSERVICENAME;

    public String getAUTHCOMPANYGUID() { return AUTHCOMPANYGUID; }
    public void setAUTHCOMPANYGUID(String v) { this.AUTHCOMPANYGUID = v; }
    public String getAUTHWEBSERVICEGUID() { return AUTHWEBSERVICEGUID; }
    public void setAUTHWEBSERVICEGUID(String v) { this.AUTHWEBSERVICEGUID = v; }

    public String getSECURITYGROUPGUID() { return SECURITYGROUPGUID; }
    public void setSECURITYGROUPGUID(String v) { this.SECURITYGROUPGUID = v; }
    public String getCOMPANYGUID() { return COMPANYGUID; }
    public void setCOMPANYGUID(String v) { this.COMPANYGUID = v; }
    public String getWEBSERVICENAME() { return WEBSERVICENAME; }
    public void setWEBSERVICENAME(String v) { this.WEBSERVICENAME = v; }
}
