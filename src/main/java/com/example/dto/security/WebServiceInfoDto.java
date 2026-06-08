package com.example.dto.security;

public class WebServiceInfoDto {
    private String webServiceGuid;
    private String webServiceName;

    public WebServiceInfoDto() {}

    public WebServiceInfoDto(String webServiceGuid, String webServiceName) {
        this.webServiceGuid = webServiceGuid;
        this.webServiceName = webServiceName;
    }

    public String getWebServiceGuid() {
        return webServiceGuid;
    }

    public void setWebServiceGuid(String webServiceGuid) {
        this.webServiceGuid = webServiceGuid;
    }

    public String getWebServiceName() {
        return webServiceName;
    }

    public void setWebServiceName(String webServiceName) {
        this.webServiceName = webServiceName;
    }
}
