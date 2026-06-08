package com.example.dto.security;

public class CompanyDto {
    private String companyGuid;
    private String companyName;

    public CompanyDto() {}

    public CompanyDto(String companyGuid, String companyName) {
        this.companyGuid = companyGuid;
        this.companyName = companyName;
    }

    public String getCompanyGuid() {
        return companyGuid;
    }

    public void setCompanyGuid(String companyGuid) {
        this.companyGuid = companyGuid;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
