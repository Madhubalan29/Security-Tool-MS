package com.example.entity.security;

import java.io.Serializable;
import java.util.Objects;

public class AsAuthPlanId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String SECURITYGROUPGUID;
    private String COMPANYGUID;
    private String PLANGUID;

    public AsAuthPlanId() {}

    public AsAuthPlanId(String SECURITYGROUPGUID, String COMPANYGUID, String PLANGUID) {
        this.SECURITYGROUPGUID = SECURITYGROUPGUID;
        this.COMPANYGUID = COMPANYGUID;
        this.PLANGUID = PLANGUID;
    }

    public String getSECURITYGROUPGUID() { return SECURITYGROUPGUID; }
    public void setSECURITYGROUPGUID(String v) { this.SECURITYGROUPGUID = v; }
    public String getCOMPANYGUID() { return COMPANYGUID; }
    public void setCOMPANYGUID(String v) { this.COMPANYGUID = v; }
    public String getPLANGUID() { return PLANGUID; }
    public void setPLANGUID(String v) { this.PLANGUID = v; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AsAuthPlanId that = (AsAuthPlanId) o;
        return Objects.equals(SECURITYGROUPGUID, that.SECURITYGROUPGUID) &&
               Objects.equals(COMPANYGUID, that.COMPANYGUID) &&
               Objects.equals(PLANGUID, that.PLANGUID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(SECURITYGROUPGUID, COMPANYGUID, PLANGUID);
    }
}
