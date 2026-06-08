package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ASAUTHWEBSERVICE")
public class AsAuthWebService {

    @Id
    private String AUTHWEBSERVICEGUID;

    private String WEBSERVICENAME;

    private String WEBSERVICEMETHOD;

    private String FILEID;

    // Getters and Setters
    public String getAUTHWEBSERVICEGUID() {
        return AUTHWEBSERVICEGUID;
    }

    public void setAUTHWEBSERVICEGUID(String aUTHWEBSERVICEGUID) {
        AUTHWEBSERVICEGUID = aUTHWEBSERVICEGUID;
    }

    public String getWEBSERVICENAME() {
        return WEBSERVICENAME;
    }

    public void setWEBSERVICENAME(String wEBSERVICENAME) {
        WEBSERVICENAME = wEBSERVICENAME;
    }

    public String getWEBSERVICEMETHOD() {
        return WEBSERVICEMETHOD;
    }

    public void setWEBSERVICEMETHOD(String wEBSERVICEMETHOD) {
        WEBSERVICEMETHOD = wEBSERVICEMETHOD;
    }

    public String getFILEID() {
        return FILEID;
    }

    public void setFILEID(String fILEID) {
        FILEID = fILEID;
    }
}
