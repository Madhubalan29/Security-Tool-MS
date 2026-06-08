package com.example.entity.security;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import org.hibernate.annotations.Formula;

@Entity
@Table(name = "ASAUTHPRODUCTTRANSACTION")
public class AsAuthProductTransaction {

    @Id
    @Column(name = "AUTHPRODUCTTRANSACTIONGUID")
    private String AUTHPRODUCTTRANSACTIONGUID;

    @Column(name = "AUTHPRODUCTGUID")
    private String AUTHPRODUCTGUID;

    @Column(name = "TRANSACTIONGUID")
    private String TRANSACTIONGUID;

    @Formula("(SELECT ac.SECURITYGROUPGUID FROM ASAUTHCOMPANY ac JOIN ASAUTHPRODUCT ap ON ac.AUTHCOMPANYGUID = ap.AUTHCOMPANYGUID WHERE ap.AUTHPRODUCTGUID = AUTHPRODUCTGUID)")
    private String SECURITYGROUPGUID;

    @Formula("(SELECT ac.COMPANYGUID FROM ASAUTHCOMPANY ac JOIN ASAUTHPRODUCT ap ON ac.AUTHCOMPANYGUID = ap.AUTHCOMPANYGUID WHERE ap.AUTHPRODUCTGUID = AUTHPRODUCTGUID)")
    private String COMPANYGUID;

    @Formula("(SELECT ap.PRODUCTGUID FROM ASAUTHPRODUCT ap WHERE ap.AUTHPRODUCTGUID = AUTHPRODUCTGUID)")
    private String PRODUCTGUID;

    @Formula("(SELECT t.TRANSACTIONNAME FROM ASTRANSACTION t WHERE t.TRANSACTIONGUID = TRANSACTIONGUID)")
    private String TRANSACTIONNAME;

    public String getAUTHPRODUCTTRANSACTIONGUID() { return AUTHPRODUCTTRANSACTIONGUID; }
    public void setAUTHPRODUCTTRANSACTIONGUID(String v) { this.AUTHPRODUCTTRANSACTIONGUID = v; }
    public String getAUTHPRODUCTGUID() { return AUTHPRODUCTGUID; }
    public void setAUTHPRODUCTGUID(String v) { this.AUTHPRODUCTGUID = v; }
    public String getTRANSACTIONGUID() { return TRANSACTIONGUID; }
    public void setTRANSACTIONGUID(String v) { this.TRANSACTIONGUID = v; }

    public String getSECURITYGROUPGUID() { return SECURITYGROUPGUID; }
    public void setSECURITYGROUPGUID(String v) { this.SECURITYGROUPGUID = v; }
    public String getCOMPANYGUID() { return COMPANYGUID; }
    public void setCOMPANYGUID(String v) { this.COMPANYGUID = v; }
    public String getPRODUCTGUID() { return PRODUCTGUID; }
    public void setPRODUCTGUID(String v) { this.PRODUCTGUID = v; }
    public String getTRANSACTIONNAME() { return TRANSACTIONNAME; }
    public void setTRANSACTIONNAME(String v) { this.TRANSACTIONNAME = v; }
}
