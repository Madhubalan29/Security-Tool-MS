package com.example.dto.security;

public class ProductDto {
    private String productGuid;
    private String productName;
    private String companyGuid;

    public ProductDto() {}

    public ProductDto(String productGuid, String productName, String companyGuid) {
        this.productGuid = productGuid;
        this.productName = productName;
        this.companyGuid = companyGuid;
    }

    public String getProductGuid() {
        return productGuid;
    }

    public void setProductGuid(String productGuid) {
        this.productGuid = productGuid;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCompanyGuid() {
        return companyGuid;
    }

    public void setCompanyGuid(String companyGuid) {
        this.companyGuid = companyGuid;
    }
}
