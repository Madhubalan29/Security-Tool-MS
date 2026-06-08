package com.example.entity.security;

import java.io.Serializable;
import java.util.Objects;

public class AsAuthCompanyWebServiceId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String AUTHCOMPANYGUID;
    private String AUTHWEBSERVICEGUID;

    public AsAuthCompanyWebServiceId() {}

    public AsAuthCompanyWebServiceId(String AUTHCOMPANYGUID, String AUTHWEBSERVICEGUID) {
        this.AUTHCOMPANYGUID = AUTHCOMPANYGUID;
        this.AUTHWEBSERVICEGUID = AUTHWEBSERVICEGUID;
    }

    public String getAUTHCOMPANYGUID() { return AUTHCOMPANYGUID; }
    public void setAUTHCOMPANYGUID(String v) { this.AUTHCOMPANYGUID = v; }
    public String getAUTHWEBSERVICEGUID() { return AUTHWEBSERVICEGUID; }
    public void setAUTHWEBSERVICEGUID(String v) { this.AUTHWEBSERVICEGUID = v; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AsAuthCompanyWebServiceId that = (AsAuthCompanyWebServiceId) o;
        return Objects.equals(AUTHCOMPANYGUID, that.AUTHCOMPANYGUID) &&
               Objects.equals(AUTHWEBSERVICEGUID, that.AUTHWEBSERVICEGUID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(AUTHCOMPANYGUID, AUTHWEBSERVICEGUID);
    }
}
