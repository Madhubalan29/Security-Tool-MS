package com.example.dto.security;

public class PlanDto {
    private String planGuid;
    private String planName;
    private String companyGuid;
    private String productGuid;

    public PlanDto() {}

    public PlanDto(String planGuid, String planName, String companyGuid, String productGuid) {
        this.planGuid = planGuid;
        this.planName = planName;
        this.companyGuid = companyGuid;
        this.productGuid = productGuid;
    }

    public String getPlanGuid() {
        return planGuid;
    }

    public void setPlanGuid(String planGuid) {
        this.planGuid = planGuid;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getCompanyGuid() {
        return companyGuid;
    }

    public void setCompanyGuid(String companyGuid) {
        this.companyGuid = companyGuid;
    }

    public String getProductGuid() {
        return productGuid;
    }

    public void setProductGuid(String productGuid) {
        this.productGuid = productGuid;
    }
}
