package com.example.entity.security;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "ASAUTHCOMPANY")
@IdClass(AsAuthCompanyId.class)
public class AsAuthCompany {

    @Id
    private String SECURITYGROUPGUID;
    @Id
    private String COMPANYGUID;

    @Column(name = "AUTHCOMPANYGUID")
    private String AUTHCOMPANYGUID;

    public String getSECURITYGROUPGUID() { return SECURITYGROUPGUID; }
    public void setSECURITYGROUPGUID(String v) { this.SECURITYGROUPGUID = v; }
    public String getCOMPANYGUID() { return COMPANYGUID; }
    public void setCOMPANYGUID(String v) { this.COMPANYGUID = v; }
    public String getAUTHCOMPANYGUID() { return AUTHCOMPANYGUID; }
    public void setAUTHCOMPANYGUID(String v) { this.AUTHCOMPANYGUID = v; }
}
