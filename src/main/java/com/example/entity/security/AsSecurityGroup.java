package com.example.entity.security;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ASSECURITYGROUP")
public class AsSecurityGroup {

    @Id
    private String SECURITYGROUPGUID;
    private String GROUPNAME;

    public String getSECURITYGROUPGUID() { return SECURITYGROUPGUID; }
    public void setSECURITYGROUPGUID(String v) { this.SECURITYGROUPGUID = v; }
    public String getGROUPNAME() { return GROUPNAME; }
    public void setGROUPNAME(String v) { this.GROUPNAME = v; }
}
