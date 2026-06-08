package com.example.entity.security;

import java.io.Serializable;
import java.util.Objects;

public class AsAuthProductId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String SECURITYGROUPGUID;
    private String COMPANYGUID;
    private String PRODUCTGUID;

    public AsAuthProductId() {}

    public AsAuthProductId(String SECURITYGROUPGUID, String COMPANYGUID, String PRODUCTGUID) {
        this.SECURITYGROUPGUID = SECURITYGROUPGUID;
        this.COMPANYGUID = COMPANYGUID;
        this.PRODUCTGUID = PRODUCTGUID;
    }

    public String getSECURITYGROUPGUID() { return SECURITYGROUPGUID; }
    public void setSECURITYGROUPGUID(String v) { this.SECURITYGROUPGUID = v; }
    public String getCOMPANYGUID() { return COMPANYGUID; }
    public void setCOMPANYGUID(String v) { this.COMPANYGUID = v; }
    public String getPRODUCTGUID() { return PRODUCTGUID; }
    public void setPRODUCTGUID(String v) { this.PRODUCTGUID = v; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AsAuthProductId that = (AsAuthProductId) o;
        return Objects.equals(SECURITYGROUPGUID, that.SECURITYGROUPGUID) &&
               Objects.equals(COMPANYGUID, that.COMPANYGUID) &&
               Objects.equals(PRODUCTGUID, that.PRODUCTGUID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(SECURITYGROUPGUID, COMPANYGUID, PRODUCTGUID);
    }
}
