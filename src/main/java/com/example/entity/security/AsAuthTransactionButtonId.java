package com.example.entity.security;

import java.io.Serializable;
import java.util.Objects;

public class AsAuthTransactionButtonId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String AUTHTRANSACTIONGUID;
    private String AUTHBUTTONGUID;

    public AsAuthTransactionButtonId() {}

    public AsAuthTransactionButtonId(String AUTHTRANSACTIONGUID, String AUTHBUTTONGUID) {
        this.AUTHTRANSACTIONGUID = AUTHTRANSACTIONGUID;
        this.AUTHBUTTONGUID = AUTHBUTTONGUID;
    }

    public String getAUTHTRANSACTIONGUID() { return AUTHTRANSACTIONGUID; }
    public void setAUTHTRANSACTIONGUID(String v) { this.AUTHTRANSACTIONGUID = v; }
    public String getAUTHBUTTONGUID() { return AUTHBUTTONGUID; }
    public void setAUTHBUTTONGUID(String v) { this.AUTHBUTTONGUID = v; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AsAuthTransactionButtonId that = (AsAuthTransactionButtonId) o;
        return Objects.equals(AUTHTRANSACTIONGUID, that.AUTHTRANSACTIONGUID) &&
               Objects.equals(AUTHBUTTONGUID, that.AUTHBUTTONGUID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(AUTHTRANSACTIONGUID, AUTHBUTTONGUID);
    }
}
