package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Lob;

@Entity
@Table(name = "ASINQUIRYSCREEN")
public class AsInquiryScreen {

    @Id
    private String INQUIRYSCREENGUID;

    private String COMPANYGUID;

    private String TYPECODE;

    private String SCREENNAME;

    @Lob
    private String XMLDATA;

    private String PLANGUID;

    private String STATECODE;

    private String PRODUCTGUID;

    private String INQUIRYSCREENNAMEGUID;

    // Getters and Setters
    public String getINQUIRYSCREENGUID() {
        return INQUIRYSCREENGUID;
    }

    public void setINQUIRYSCREENGUID(String iNQUIRYSCREENGUID) {
        INQUIRYSCREENGUID = iNQUIRYSCREENGUID;
    }

    public String getCOMPANYGUID() {
        return COMPANYGUID;
    }

    public void setCOMPANYGUID(String cOMPANYGUID) {
        COMPANYGUID = cOMPANYGUID;
    }

    public String getTYPECODE() {
        return TYPECODE;
    }

    public void setTYPECODE(String tYPECODE) {
        TYPECODE = tYPECODE;
    }

    public String getSCREENNAME() {
        return SCREENNAME;
    }

    public void setSCREENNAME(String sCREENNAME) {
        SCREENNAME = sCREENNAME;
    }

    public String getXMLDATA() {
        return XMLDATA;
    }

    public void setXMLDATA(String xMLDATA) {
        XMLDATA = xMLDATA;
    }

    public String getPLANGUID() {
        return PLANGUID;
    }

    public void setPLANGUID(String pLANGUID) {
        PLANGUID = pLANGUID;
    }

    public String getSTATECODE() {
        return STATECODE;
    }

    public void setSTATECODE(String sTATECODE) {
        STATECODE = sTATECODE;
    }

    public String getPRODUCTGUID() {
        return PRODUCTGUID;
    }

    public void setPRODUCTGUID(String pRODUCTGUID) {
        PRODUCTGUID = pRODUCTGUID;
    }

    public String getINQUIRYSCREENNAMEGUID() {
        return INQUIRYSCREENNAMEGUID;
    }

    public void setINQUIRYSCREENNAMEGUID(String iNQUIRYSCREENNAMEGUID) {
        INQUIRYSCREENNAMEGUID = iNQUIRYSCREENNAMEGUID;
    }
}
