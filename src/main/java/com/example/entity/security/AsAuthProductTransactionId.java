package com.example.entity.security;

import java.io.Serializable;
import java.util.Objects;

public class AsAuthProductTransactionId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String SECURITYGROUPGUID;
    private String COMPANYGUID;
    private String PRODUCTGUID;
    private String TRANSACTIONNAME;

    public AsAuthProductTransactionId() {}

    public AsAuthProductTransactionId(String SECURITYGROUPGUID, String COMPANYGUID, String PRODUCTGUID, String TRANSACTIONNAME) {
        this.SECURITYGROUPGUID = SECURITYGROUPGUID;
        this.COMPANYGUID = COMPANYGUID;
        this.PRODUCTGUID = PRODUCTGUID;
        this.TRANSACTIONNAME = TRANSACTIONNAME;
    }

    public String getSECURITYGROUPGUID() { return SECURITYGROUPGUID; }
    public void setSECURITYGROUPGUID(String v) { this.SECURITYGROUPGUID = v; }
    public String getCOMPANYGUID() { return COMPANYGUID; }
    public void setCOMPANYGUID(String v) { this.COMPANYGUID = v; }
    public String getPRODUCTGUID() { return PRODUCTGUID; }
    public void setPRODUCTGUID(String v) { this.PRODUCTGUID = v; }
    public String getTRANSACTIONNAME() { return TRANSACTIONNAME; }
    public void setTRANSACTIONNAME(String v) { this.TRANSACTIONNAME = v; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AsAuthProductTransactionId that = (AsAuthProductTransactionId) o;
        return Objects.equals(SECURITYGROUPGUID, that.SECURITYGROUPGUID) &&
               Objects.equals(COMPANYGUID, that.COMPANYGUID) &&
               Objects.equals(PRODUCTGUID, that.PRODUCTGUID) &&
               Objects.equals(TRANSACTIONNAME, that.TRANSACTIONNAME);
    }

    @Override
    public int hashCode() {
        return Objects.hash(SECURITYGROUPGUID, COMPANYGUID, PRODUCTGUID, TRANSACTIONNAME);
    }
}
