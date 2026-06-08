package com.example.entity.security;

import java.io.Serializable;
import java.util.Objects;

public class AsAuthCompanyInquiryId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String SECURITYGROUPGUID;
    private String COMPANYGUID;
    private String INQUIRYNAME;

    public AsAuthCompanyInquiryId() {}

    public AsAuthCompanyInquiryId(String SECURITYGROUPGUID, String COMPANYGUID, String INQUIRYNAME) {
        this.SECURITYGROUPGUID = SECURITYGROUPGUID;
        this.COMPANYGUID = COMPANYGUID;
        this.INQUIRYNAME = INQUIRYNAME;
    }

    public String getSECURITYGROUPGUID() { return SECURITYGROUPGUID; }
    public void setSECURITYGROUPGUID(String v) { this.SECURITYGROUPGUID = v; }
    public String getCOMPANYGUID() { return COMPANYGUID; }
    public void setCOMPANYGUID(String v) { this.COMPANYGUID = v; }
    public String getINQUIRYNAME() { return INQUIRYNAME; }
    public void setINQUIRYNAME(String v) { this.INQUIRYNAME = v; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AsAuthCompanyInquiryId that = (AsAuthCompanyInquiryId) o;
        return Objects.equals(SECURITYGROUPGUID, that.SECURITYGROUPGUID) &&
               Objects.equals(COMPANYGUID, that.COMPANYGUID) &&
               Objects.equals(INQUIRYNAME, that.INQUIRYNAME);
    }

    @Override
    public int hashCode() {
        return Objects.hash(SECURITYGROUPGUID, COMPANYGUID, INQUIRYNAME);
    }
}
