package com.example.entity.security;

import java.io.Serializable;
import java.util.Objects;

public class AsAuthCompanyPageId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String SECURITYGROUPGUID;
    private String COMPANYGUID;
    private String PAGENAME;

    public AsAuthCompanyPageId() {}

    public AsAuthCompanyPageId(String SECURITYGROUPGUID, String COMPANYGUID, String PAGENAME) {
        this.SECURITYGROUPGUID = SECURITYGROUPGUID;
        this.COMPANYGUID = COMPANYGUID;
        this.PAGENAME = PAGENAME;
    }

    public String getSECURITYGROUPGUID() { return SECURITYGROUPGUID; }
    public void setSECURITYGROUPGUID(String v) { this.SECURITYGROUPGUID = v; }
    public String getCOMPANYGUID() { return COMPANYGUID; }
    public void setCOMPANYGUID(String v) { this.COMPANYGUID = v; }
    public String getPAGENAME() { return PAGENAME; }
    public void setPAGENAME(String v) { this.PAGENAME = v; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AsAuthCompanyPageId that = (AsAuthCompanyPageId) o;
        return Objects.equals(SECURITYGROUPGUID, that.SECURITYGROUPGUID) &&
               Objects.equals(COMPANYGUID, that.COMPANYGUID) &&
               Objects.equals(PAGENAME, that.PAGENAME);
    }

    @Override
    public int hashCode() {
        return Objects.hash(SECURITYGROUPGUID, COMPANYGUID, PAGENAME);
    }
}
