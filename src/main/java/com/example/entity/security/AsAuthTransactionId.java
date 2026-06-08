package com.example.entity.security;

import java.io.Serializable;
import java.util.Objects;

public class AsAuthTransactionId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String SECURITYGROUPGUID;
    private String COMPANYGUID;
    private String PLANGUID;
    private String TRANSACTIONNAME;

    public AsAuthTransactionId() {}

    public AsAuthTransactionId(String SECURITYGROUPGUID, String COMPANYGUID, String PLANGUID, String TRANSACTIONNAME) {
        this.SECURITYGROUPGUID = SECURITYGROUPGUID;
        this.COMPANYGUID = COMPANYGUID;
        this.PLANGUID = PLANGUID;
        this.TRANSACTIONNAME = TRANSACTIONNAME;
    }

    public String getSECURITYGROUPGUID() { return SECURITYGROUPGUID; }
    public void setSECURITYGROUPGUID(String v) { this.SECURITYGROUPGUID = v; }
    public String getCOMPANYGUID() { return COMPANYGUID; }
    public void setCOMPANYGUID(String v) { this.COMPANYGUID = v; }
    public String getPLANGUID() { return PLANGUID; }
    public void setPLANGUID(String v) { this.PLANGUID = v; }
    public String getTRANSACTIONNAME() { return TRANSACTIONNAME; }
    public void setTRANSACTIONNAME(String v) { this.TRANSACTIONNAME = v; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AsAuthTransactionId that = (AsAuthTransactionId) o;
        return Objects.equals(SECURITYGROUPGUID, that.SECURITYGROUPGUID) &&
               Objects.equals(COMPANYGUID, that.COMPANYGUID) &&
               Objects.equals(PLANGUID, that.PLANGUID) &&
               Objects.equals(TRANSACTIONNAME, that.TRANSACTIONNAME);
    }

    @Override
    public int hashCode() {
        return Objects.hash(SECURITYGROUPGUID, COMPANYGUID, PLANGUID, TRANSACTIONNAME);
    }
}
