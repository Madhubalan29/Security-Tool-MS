package com.example.entity.security;

import java.io.Serializable;
import java.util.Objects;

public class AsAuthProductPageId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String SECURITYGROUPGUID;
    private String COMPANYGUID;
    private String PRODUCTGUID;
    private String PAGENAME;

    public AsAuthProductPageId() {}

    public AsAuthProductPageId(String SECURITYGROUPGUID, String COMPANYGUID, String PRODUCTGUID, String PAGENAME) {
        this.SECURITYGROUPGUID = SECURITYGROUPGUID;
        this.COMPANYGUID = COMPANYGUID;
        this.PRODUCTGUID = PRODUCTGUID;
        this.PAGENAME = PAGENAME;
    }

    public String getSECURITYGROUPGUID() { return SECURITYGROUPGUID; }
    public void setSECURITYGROUPGUID(String v) { this.SECURITYGROUPGUID = v; }
    public String getCOMPANYGUID() { return COMPANYGUID; }
    public void setCOMPANYGUID(String v) { this.COMPANYGUID = v; }
    public String getPRODUCTGUID() { return PRODUCTGUID; }
    public void setPRODUCTGUID(String v) { this.PRODUCTGUID = v; }
    public String getPAGENAME() { return PAGENAME; }
    public void setPAGENAME(String v) { this.PAGENAME = v; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AsAuthProductPageId that = (AsAuthProductPageId) o;
        return Objects.equals(SECURITYGROUPGUID, that.SECURITYGROUPGUID) &&
               Objects.equals(COMPANYGUID, that.COMPANYGUID) &&
               Objects.equals(PRODUCTGUID, that.PRODUCTGUID) &&
               Objects.equals(PAGENAME, that.PAGENAME);
    }

    @Override
    public int hashCode() {
        return Objects.hash(SECURITYGROUPGUID, COMPANYGUID, PRODUCTGUID, PAGENAME);
    }
}
