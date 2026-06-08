package com.example.entity.security;

import java.io.Serializable;
import java.util.Objects;

public class AsAuthPlanPageId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String SECURITYGROUPGUID;
    private String COMPANYGUID;
    private String PLANGUID;
    private String PAGENAME;

    public AsAuthPlanPageId() {}

    public AsAuthPlanPageId(String SECURITYGROUPGUID, String COMPANYGUID, String PLANGUID, String PAGENAME) {
        this.SECURITYGROUPGUID = SECURITYGROUPGUID;
        this.COMPANYGUID = COMPANYGUID;
        this.PLANGUID = PLANGUID;
        this.PAGENAME = PAGENAME;
    }

    public String getSECURITYGROUPGUID() { return SECURITYGROUPGUID; }
    public void setSECURITYGROUPGUID(String v) { this.SECURITYGROUPGUID = v; }
    public String getCOMPANYGUID() { return COMPANYGUID; }
    public void setCOMPANYGUID(String v) { this.COMPANYGUID = v; }
    public String getPLANGUID() { return PLANGUID; }
    public void setPLANGUID(String v) { this.PLANGUID = v; }
    public String getPAGENAME() { return PAGENAME; }
    public void setPAGENAME(String v) { this.PAGENAME = v; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AsAuthPlanPageId that = (AsAuthPlanPageId) o;
        return Objects.equals(SECURITYGROUPGUID, that.SECURITYGROUPGUID) &&
               Objects.equals(COMPANYGUID, that.COMPANYGUID) &&
               Objects.equals(PLANGUID, that.PLANGUID) &&
               Objects.equals(PAGENAME, that.PAGENAME);
    }

    @Override
    public int hashCode() {
        return Objects.hash(SECURITYGROUPGUID, COMPANYGUID, PLANGUID, PAGENAME);
    }
}
