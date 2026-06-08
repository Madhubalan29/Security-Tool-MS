package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ASTRANSACTION")
public class AsTransaction {

    @Id
    private String TRANSACTIONGUID;

    private String TRANSACTIONNAME;

    private String PRODUCTGUID;

    private String PLANGUID;

    public String getTRANSACTIONGUID() {
        return TRANSACTIONGUID;
    }

    public void setTRANSACTIONGUID(String tRANSACTIONGUID) {
        TRANSACTIONGUID = tRANSACTIONGUID;
    }

    public String getTRANSACTIONNAME() {
        return TRANSACTIONNAME;
    }

    public void setTRANSACTIONNAME(String tRANSACTIONNAME) {
        TRANSACTIONNAME = tRANSACTIONNAME;
    }

    public String getPRODUCTGUID() {
        return PRODUCTGUID;
    }

    public void setPRODUCTGUID(String pRODUCTGUID) {
        PRODUCTGUID = pRODUCTGUID;
    }

    public String getPLANGUID() {
        return PLANGUID;
    }

    public void setPLANGUID(String pLANGUID) {
        PLANGUID = pLANGUID;
    }
}
