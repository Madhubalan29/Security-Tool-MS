package com.example.entity.security;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import org.hibernate.annotations.Formula;

@Entity
@Table(name = "ASAUTHTRANSACTION")
public class AsAuthTransaction {

    @Id
    @Column(name = "AUTHTRANSACTIONGUID")
    private String AUTHTRANSACTIONGUID;

    @Column(name = "AUTHPLANGUID")
    private String AUTHPLANGUID;

    @Column(name = "TRANSACTIONGUID")
    private String TRANSACTIONGUID;

    @Formula("(SELECT ac.SECURITYGROUPGUID FROM ASAUTHCOMPANY ac JOIN ASAUTHPLAN ap ON ac.AUTHCOMPANYGUID = ap.AUTHCOMPANYGUID WHERE ap.AUTHPLANGUID = AUTHPLANGUID)")
    private String SECURITYGROUPGUID;

    @Formula("(SELECT ac.COMPANYGUID FROM ASAUTHCOMPANY ac JOIN ASAUTHPLAN ap ON ac.AUTHCOMPANYGUID = ap.AUTHCOMPANYGUID WHERE ap.AUTHPLANGUID = AUTHPLANGUID)")
    private String COMPANYGUID;

    @Formula("(SELECT ap.PLANGUID FROM ASAUTHPLAN ap WHERE ap.AUTHPLANGUID = AUTHPLANGUID)")
    private String PLANGUID;

    @Formula("(SELECT t.TRANSACTIONNAME FROM ASTRANSACTION t WHERE t.TRANSACTIONGUID = TRANSACTIONGUID)")
    private String TRANSACTIONNAME;

    public String getAUTHTRANSACTIONGUID() { return AUTHTRANSACTIONGUID; }
    public void setAUTHTRANSACTIONGUID(String v) { this.AUTHTRANSACTIONGUID = v; }
    public String getAUTHPLANGUID() { return AUTHPLANGUID; }
    public void setAUTHPLANGUID(String v) { this.AUTHPLANGUID = v; }
    public String getTRANSACTIONGUID() { return TRANSACTIONGUID; }
    public void setTRANSACTIONGUID(String v) { this.TRANSACTIONGUID = v; }

    public String getSECURITYGROUPGUID() { return SECURITYGROUPGUID; }
    public void setSECURITYGROUPGUID(String v) { this.SECURITYGROUPGUID = v; }
    public String getCOMPANYGUID() { return COMPANYGUID; }
    public void setCOMPANYGUID(String v) { this.COMPANYGUID = v; }
    public String getPLANGUID() { return PLANGUID; }
    public void setPLANGUID(String v) { this.PLANGUID = v; }
    public String getTRANSACTIONNAME() { return TRANSACTIONNAME; }
    public void setTRANSACTIONNAME(String v) { this.TRANSACTIONNAME = v; }
}
