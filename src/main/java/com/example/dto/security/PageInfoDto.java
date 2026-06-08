package com.example.dto.security;

public class PageInfoDto {
    private String pageGuid;
    private String pageName;

    public PageInfoDto() {}

    public PageInfoDto(String pageGuid, String pageName) {
        this.pageGuid = pageGuid;
        this.pageName = pageName;
    }

    public String getPageGuid() {
        return pageGuid;
    }

    public void setPageGuid(String pageGuid) {
        this.pageGuid = pageGuid;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }
}
