package com.example.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ASCOMPANY")
public class AsCompany {

    @Id
    private String COMPANYGUID;

    private String COMPANYNAME;

    private LocalDateTime EFFECTIVEDATE;

    private String CLIENTGUID;

    private String DEFAULTCURRENCYCODE;

    private String MARKETMAKERGUID;

    private String CALENDARCODE;

    // Getters and Setters
    public String getCOMPANYGUID() {
        return COMPANYGUID;
    }

    public void setCOMPANYGUID(String cOMPANYGUID) {
        COMPANYGUID = cOMPANYGUID;
    }

    public String getCOMPANYNAME() {
        return COMPANYNAME;
    }

    public void setCOMPANYNAME(String cOMPANYNAME) {
        COMPANYNAME = cOMPANYNAME;
    }

    public LocalDateTime getEFFECTIVEDATE() {
        return EFFECTIVEDATE;
    }

    public void setEFFECTIVEDATE(LocalDateTime eFFECTIVEDATE) {
        EFFECTIVEDATE = eFFECTIVEDATE;
    }

    public String getCLIENTGUID() {
        return CLIENTGUID;
    }

    public void setCLIENTGUID(String cLIENTGUID) {
        CLIENTGUID = cLIENTGUID;
    }

    public String getDEFAULTCURRENCYCODE() {
        return DEFAULTCURRENCYCODE;
    }

    public void setDEFAULTCURRENCYCODE(String dEFAULTCURRENCYCODE) {
        DEFAULTCURRENCYCODE = dEFAULTCURRENCYCODE;
    }

    public String getMARKETMAKERGUID() {
        return MARKETMAKERGUID;
    }

    public void setMARKETMAKERGUID(String mARKETMAKERGUID) {
        MARKETMAKERGUID = mARKETMAKERGUID;
    }

    public String getCALENDARCODE() {
        return CALENDARCODE;
    }

    public void setCALENDARCODE(String cALENDARCODE) {
        CALENDARCODE = cALENDARCODE;
    }
}
