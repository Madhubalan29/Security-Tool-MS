package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "AsRateGroup")
public class AsRateGroup {

    @Id
    @Column(name = "RATEGROUPGUID", length = 36, nullable = false)
    private String rateGroupGuid;

    @Column(name = "RATEDESCRIPTION", length = 300, nullable = false)
    private String rateDescription;

    @Column(name = "INTEGERCRITERIA", length = 60, nullable = false)
    private String integerCriteria;

    @Column(name = "CRITERIA1", length = 210)
    private String criteria1;

    @Column(name = "CRITERIA2", length = 210)
    private String criteria2;

    @Column(name = "CRITERIA3", length = 210)
    private String criteria3;

    @Column(name = "CRITERIA4", length = 210)
    private String criteria4;

    @Column(name = "CRITERIA5", length = 210)
    private String criteria5;

    @Column(name = "CRITERIA6", length = 210)
    private String criteria6;

    @Column(name = "CRITERIA7", length = 210)
    private String criteria7;

    @Column(name = "CRITERIA8", length = 210)
    private String criteria8;

    @Column(name = "CRITERIA9", length = 210)
    private String criteria9;

    @Column(name = "CRITERIA10", length = 210)
    private String criteria10;

    @Column(name = "STATUSCODE", length = 2, nullable = false)
    private String statusCode;

    @Column(name = "TYPECODE", length = 2, nullable = false)
    private String typeCode;

    @Column(name = "EFFECTIVEDATE")
    private Timestamp effectiveDate;

    @Column(name = "ACTIVEFROMDATE")
    private Timestamp activeFromDate;

    @Column(name = "ACTIVETODATE")
    private Timestamp activeToDate;

    @Lob
    @Column(name = "XMLDATA")
    private String xmlData;

    @Column(name = "EXPIRATIONDATE")
    private Timestamp expirationDate;

    @Column(name = "TABLEFORMAT", length = 10)
    private String tableFormat;

    @Column(name = "SECONDARYINDEX", length = 210)
    private String secondaryIndex;

    public String getRateGroupGuid() {
        return rateGroupGuid;
    }

    public void setRateGroupGuid(String rateGroupGuid) {
        this.rateGroupGuid = rateGroupGuid;
    }

    public String getRateDescription() {
        return rateDescription;
    }

    public void setRateDescription(String rateDescription) {
        this.rateDescription = rateDescription;
    }

    public String getIntegerCriteria() {
        return integerCriteria;
    }

    public void setIntegerCriteria(String integerCriteria) {
        this.integerCriteria = integerCriteria;
    }

    public String getCriteria1() {
        return criteria1;
    }

    public void setCriteria1(String criteria1) {
        this.criteria1 = criteria1;
    }

    public String getCriteria2() {
        return criteria2;
    }

    public void setCriteria2(String criteria2) {
        this.criteria2 = criteria2;
    }

    public String getCriteria3() {
        return criteria3;
    }

    public void setCriteria3(String criteria3) {
        this.criteria3 = criteria3;
    }

    public String getCriteria4() {
        return criteria4;
    }

    public void setCriteria4(String criteria4) {
        this.criteria4 = criteria4;
    }

    public String getCriteria5() {
        return criteria5;
    }

    public void setCriteria5(String criteria5) {
        this.criteria5 = criteria5;
    }

    public String getCriteria6() {
        return criteria6;
    }

    public void setCriteria6(String criteria6) {
        this.criteria6 = criteria6;
    }

    public String getCriteria7() {
        return criteria7;
    }

    public void setCriteria7(String criteria7) {
        this.criteria7 = criteria7;
    }

    public String getCriteria8() {
        return criteria8;
    }

    public void setCriteria8(String criteria8) {
        this.criteria8 = criteria8;
    }

    public String getCriteria9() {
        return criteria9;
    }

    public void setCriteria9(String criteria9) {
        this.criteria9 = criteria9;
    }

    public String getCriteria10() {
        return criteria10;
    }

    public void setCriteria10(String criteria10) {
        this.criteria10 = criteria10;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public Timestamp getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Timestamp effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Timestamp getActiveFromDate() {
        return activeFromDate;
    }

    public void setActiveFromDate(Timestamp activeFromDate) {
        this.activeFromDate = activeFromDate;
    }

    public Timestamp getActiveToDate() {
        return activeToDate;
    }

    public void setActiveToDate(Timestamp activeToDate) {
        this.activeToDate = activeToDate;
    }

    public String getXmlData() {
        return xmlData;
    }

    public void setXmlData(String xmlData) {
        this.xmlData = xmlData;
    }

    public Timestamp getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Timestamp expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getTableFormat() {
        return tableFormat;
    }

    public void setTableFormat(String tableFormat) {
        this.tableFormat = tableFormat;
    }

    public String getSecondaryIndex() {
        return secondaryIndex;
    }

    public void setSecondaryIndex(String secondaryIndex) {
        this.secondaryIndex = secondaryIndex;
    }
}
