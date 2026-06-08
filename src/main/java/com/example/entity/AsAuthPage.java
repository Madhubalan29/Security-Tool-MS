package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ASAUTHPAGE")
public class AsAuthPage {

    @Id
    private String AUTHPAGEGUID;

    private String PAGENAME;

    // Getters and Setters
    public String getAUTHPAGEGUID() {
        return AUTHPAGEGUID;
    }

    public void setAUTHPAGEGUID(String aUTHPAGEGUID) {
        AUTHPAGEGUID = aUTHPAGEGUID;
    }

    public String getPAGENAME() {
        return PAGENAME;
    }

    public void setPAGENAME(String pAGENAME) {
        PAGENAME = pAGENAME;
    }
}
