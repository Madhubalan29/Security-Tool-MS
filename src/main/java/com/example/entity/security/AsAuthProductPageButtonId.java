package com.example.entity.security;

import java.io.Serializable;
import java.util.Objects;

public class AsAuthProductPageButtonId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String AUTHPRODUCTPAGEGUID;
    private String AUTHBUTTONGUID;

    public AsAuthProductPageButtonId() {}

    public AsAuthProductPageButtonId(String AUTHPRODUCTPAGEGUID, String AUTHBUTTONGUID) {
        this.AUTHPRODUCTPAGEGUID = AUTHPRODUCTPAGEGUID;
        this.AUTHBUTTONGUID = AUTHBUTTONGUID;
    }

    public String getAUTHPRODUCTPAGEGUID() { return AUTHPRODUCTPAGEGUID; }
    public void setAUTHPRODUCTPAGEGUID(String v) { this.AUTHPRODUCTPAGEGUID = v; }
    public String getAUTHBUTTONGUID() { return AUTHBUTTONGUID; }
    public void setAUTHBUTTONGUID(String v) { this.AUTHBUTTONGUID = v; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AsAuthProductPageButtonId that = (AsAuthProductPageButtonId) o;
        return Objects.equals(AUTHPRODUCTPAGEGUID, that.AUTHPRODUCTPAGEGUID) &&
               Objects.equals(AUTHBUTTONGUID, that.AUTHBUTTONGUID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(AUTHPRODUCTPAGEGUID, AUTHBUTTONGUID);
    }
}
