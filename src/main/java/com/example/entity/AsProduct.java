package com.example.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ASPRODUCT")
public class AsProduct {

    @Id
    private String PRODUCTGUID;

    private String PRODUCTNAME;

    private String PRODUCTDESCRIPTION;

    private String COMPANYGUID;

    private LocalDateTime EFFECTIVEFROM;

    private LocalDateTime EFFECTIVETO;

    // Getters and Setters
    public String getPRODUCTGUID() {
        return PRODUCTGUID;
    }

    public void setPRODUCTGUID(String pRODUCTGUID) {
        PRODUCTGUID = pRODUCTGUID;
    }

    public String getPRODUCTNAME() {
        return PRODUCTNAME;
    }

    public void setPRODUCTNAME(String pRODUCTNAME) {
        PRODUCTNAME = pRODUCTNAME;
    }

    public String getPRODUCTDESCRIPTION() {
        return PRODUCTDESCRIPTION;
    }

    public void setPRODUCTDESCRIPTION(String pRODUCTDESCRIPTION) {
        PRODUCTDESCRIPTION = pRODUCTDESCRIPTION;
    }

    public String getCOMPANYGUID() {
        return COMPANYGUID;
    }

    public void setCOMPANYGUID(String cOMPANYGUID) {
        COMPANYGUID = cOMPANYGUID;
    }

    public LocalDateTime getEFFECTIVEFROM() {
        return EFFECTIVEFROM;
    }

    public void setEFFECTIVEFROM(LocalDateTime eFFECTIVEFROM) {
        EFFECTIVEFROM = eFFECTIVEFROM;
    }

    public LocalDateTime getEFFECTIVETO() {
        return EFFECTIVETO;
    }

    public void setEFFECTIVETO(LocalDateTime eFFECTIVETO) {
        EFFECTIVETO = eFFECTIVETO;
    }
}
