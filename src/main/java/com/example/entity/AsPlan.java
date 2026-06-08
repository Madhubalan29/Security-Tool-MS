package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ASPLAN")
public class AsPlan {

    @Id
    private String PLANGUID;

    private String COMPANYGUID;

    private String PLANNAME;

    private String PRODUCTGUID;

    public String getPLANGUID() {
        return PLANGUID;
    }

    public void setPLANGUID(String pLANGUID) {
        PLANGUID = pLANGUID;
    }

    public String getCOMPANYGUID() {
        return COMPANYGUID;
    }

    public void setCOMPANYGUID(String cOMPANYGUID) {
        COMPANYGUID = cOMPANYGUID;
    }

    public String getPLANNAME() {
        return PLANNAME;
    }

    public void setPLANNAME(String pLANNAME) {
        PLANNAME = pLANNAME;
    }

    public String getPRODUCTGUID() {
        return PRODUCTGUID;
    }

    public void setPRODUCTGUID(String pRODUCTGUID) {
        PRODUCTGUID = pRODUCTGUID;
    }
}
