package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ASAUTHBUTTON")
public class AsAuthButton {

    @Id
    private String AUTHBUTTONGUID;

    private String BUTTONNAME;

    // Getters and Setters
    public String getAUTHBUTTONGUID() {
        return AUTHBUTTONGUID;
    }

    public void setAUTHBUTTONGUID(String aUTHBUTTONGUID) {
        AUTHBUTTONGUID = aUTHBUTTONGUID;
    }

    public String getBUTTONNAME() {
        return BUTTONNAME;
    }

    public void setBUTTONNAME(String bUTTONNAME) {
        BUTTONNAME = bUTTONNAME;
    }
}
