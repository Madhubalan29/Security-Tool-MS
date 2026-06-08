package com.example.entity.security;

import java.io.Serializable;
import java.util.Objects;

public class AsAuthPlanInquiryId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String SECURITYGROUPGUID;
    private String COMPANYGUID;
    private String PLANGUID;
    private String INQUIRYNAME;

    public AsAuthPlanInquiryId() {}

    public AsAuthPlanInquiryId(String SECURITYGROUPGUID, String COMPANYGUID, String PLANGUID, String INQUIRYNAME) {
        this.SECURITYGROUPGUID = SECURITYGROUPGUID;
        this.COMPANYGUID = COMPANYGUID;
        this.PLANGUID = PLANGUID;
        this.INQUIRYNAME = INQUIRYNAME;
    }

    public String getSECURITYGROUPGUID() { return SECURITYGROUPGUID; }
    public void setSECURITYGROUPGUID(String v) { this.SECURITYGROUPGUID = v; }
    public String getCOMPANYGUID() { return COMPANYGUID; }
    public void setCOMPANYGUID(String v) { this.COMPANYGUID = v; }
    public String getPLANGUID() { return PLANGUID; }
    public void setPLANGUID(String v) { this.PLANGUID = v; }
    public String getINQUIRYNAME() { return INQUIRYNAME; }
    public void setINQUIRYNAME(String v) { this.INQUIRYNAME = v; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AsAuthPlanInquiryId that = (AsAuthPlanInquiryId) o;
        return Objects.equals(SECURITYGROUPGUID, that.SECURITYGROUPGUID) &&
               Objects.equals(COMPANYGUID, that.COMPANYGUID) &&
               Objects.equals(PLANGUID, that.PLANGUID) &&
               Objects.equals(INQUIRYNAME, that.INQUIRYNAME);
    }

    @Override
    public int hashCode() {
        return Objects.hash(SECURITYGROUPGUID, COMPANYGUID, PLANGUID, INQUIRYNAME);
    }
}
