package com.example.entity.security;

import java.io.Serializable;
import java.util.Objects;

public class AsAuthCompanyPageButtonId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String SECURITYGROUPGUID;
    private String COMPANYGUID;
    private String PAGENAME;
    private String BUTTONNAME;

    public AsAuthCompanyPageButtonId() {}

    public AsAuthCompanyPageButtonId(String SECURITYGROUPGUID, String COMPANYGUID, String PAGENAME, String BUTTONNAME) {
        this.SECURITYGROUPGUID = SECURITYGROUPGUID;
        this.COMPANYGUID = COMPANYGUID;
        this.PAGENAME = PAGENAME;
        this.BUTTONNAME = BUTTONNAME;
    }

    public String getSECURITYGROUPGUID() { return SECURITYGROUPGUID; }
    public void setSECURITYGROUPGUID(String v) { this.SECURITYGROUPGUID = v; }
    public String getCOMPANYGUID() { return COMPANYGUID; }
    public void setCOMPANYGUID(String v) { this.COMPANYGUID = v; }
    public String getPAGENAME() { return PAGENAME; }
    public void setPAGENAME(String v) { this.PAGENAME = v; }
    public String getBUTTONNAME() { return BUTTONNAME; }
    public void setBUTTONNAME(String v) { this.BUTTONNAME = v; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AsAuthCompanyPageButtonId that = (AsAuthCompanyPageButtonId) o;
        return Objects.equals(SECURITYGROUPGUID, that.SECURITYGROUPGUID) &&
               Objects.equals(COMPANYGUID, that.COMPANYGUID) &&
               Objects.equals(PAGENAME, that.PAGENAME) &&
               Objects.equals(BUTTONNAME, that.BUTTONNAME);
    }

    @Override
    public int hashCode() {
        return Objects.hash(SECURITYGROUPGUID, COMPANYGUID, PAGENAME, BUTTONNAME);
    }
}
