package com.example.entity.security;

import java.io.Serializable;
import java.util.Objects;

public class AsAuthPlanPageButtonId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String AUTHPLANPAGEGUID;
    private String AUTHBUTTONGUID;

    public AsAuthPlanPageButtonId() {}

    public AsAuthPlanPageButtonId(String AUTHPLANPAGEGUID, String AUTHBUTTONGUID) {
        this.AUTHPLANPAGEGUID = AUTHPLANPAGEGUID;
        this.AUTHBUTTONGUID = AUTHBUTTONGUID;
    }

    public String getAUTHPLANPAGEGUID() { return AUTHPLANPAGEGUID; }
    public void setAUTHPLANPAGEGUID(String v) { this.AUTHPLANPAGEGUID = v; }
    public String getAUTHBUTTONGUID() { return AUTHBUTTONGUID; }
    public void setAUTHBUTTONGUID(String v) { this.AUTHBUTTONGUID = v; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AsAuthPlanPageButtonId that = (AsAuthPlanPageButtonId) o;
        return Objects.equals(AUTHPLANPAGEGUID, that.AUTHPLANPAGEGUID) &&
               Objects.equals(AUTHBUTTONGUID, that.AUTHBUTTONGUID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(AUTHPLANPAGEGUID, AUTHBUTTONGUID);
    }
}
