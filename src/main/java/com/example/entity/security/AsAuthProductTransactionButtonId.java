package com.example.entity.security;

import java.io.Serializable;
import java.util.Objects;

public class AsAuthProductTransactionButtonId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String AUTHPRODUCTTRANSACTIONGUID;
    private String AUTHBUTTONGUID;

    public AsAuthProductTransactionButtonId() {}

    public AsAuthProductTransactionButtonId(String AUTHPRODUCTTRANSACTIONGUID, String AUTHBUTTONGUID) {
        this.AUTHPRODUCTTRANSACTIONGUID = AUTHPRODUCTTRANSACTIONGUID;
        this.AUTHBUTTONGUID = AUTHBUTTONGUID;
    }

    public String getAUTHPRODUCTTRANSACTIONGUID() { return AUTHPRODUCTTRANSACTIONGUID; }
    public void setAUTHPRODUCTTRANSACTIONGUID(String v) { this.AUTHPRODUCTTRANSACTIONGUID = v; }
    public String getAUTHBUTTONGUID() { return AUTHBUTTONGUID; }
    public void setAUTHBUTTONGUID(String v) { this.AUTHBUTTONGUID = v; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AsAuthProductTransactionButtonId that = (AsAuthProductTransactionButtonId) o;
        return Objects.equals(AUTHPRODUCTTRANSACTIONGUID, that.AUTHPRODUCTTRANSACTIONGUID) &&
               Objects.equals(AUTHBUTTONGUID, that.AUTHBUTTONGUID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(AUTHPRODUCTTRANSACTIONGUID, AUTHBUTTONGUID);
    }
}
