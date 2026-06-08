package com.example.dto.security;

public class WebServiceDto {
    private String webServiceGuid;

    public WebServiceDto() {}

    public WebServiceDto(String webServiceGuid) {
        this.webServiceGuid = webServiceGuid;
    }

    public String getWebServiceGuid() { return webServiceGuid; }
    public void setWebServiceGuid(String webServiceGuid) { this.webServiceGuid = webServiceGuid; }
}
