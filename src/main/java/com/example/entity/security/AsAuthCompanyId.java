package com.example.entity.security;

import java.io.Serializable;
import java.util.Objects;

public class AsAuthCompanyId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String SECURITYGROUPGUID;
    private String COMPANYGUID;

    public AsAuthCompanyId() {}

    public AsAuthCompanyId(String SECURITYGROUPGUID, String COMPANYGUID) {
        this.SECURITYGROUPGUID = SECURITYGROUPGUID;
        this.COMPANYGUID = COMPANYGUID;
    }

    public String getSECURITYGROUPGUID() { return SECURITYGROUPGUID; }
    public void setSECURITYGROUPGUID(String SECURITYGROUPGUID) { this.SECURITYGROUPGUID = SECURITYGROUPGUID; }
    public String getCOMPANYGUID() { return COMPANYGUID; }
    public void setCOMPANYGUID(String COMPANYGUID) { this.COMPANYGUID = COMPANYGUID; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AsAuthCompanyId that = (AsAuthCompanyId) o;
        return Objects.equals(SECURITYGROUPGUID, that.SECURITYGROUPGUID) &&
               Objects.equals(COMPANYGUID, that.COMPANYGUID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(SECURITYGROUPGUID, COMPANYGUID);
    }
}
