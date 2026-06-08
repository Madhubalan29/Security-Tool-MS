package com.example.dto.security;

public class InquiryScreenDto {
    private String inquiryScreenGuid;
    private String screenName;

    public InquiryScreenDto() {}

    public InquiryScreenDto(String inquiryScreenGuid, String screenName) {
        this.inquiryScreenGuid = inquiryScreenGuid;
        this.screenName = screenName;
    }

    public String getInquiryScreenGuid() {
        return inquiryScreenGuid;
    }

    public void setInquiryScreenGuid(String inquiryScreenGuid) {
        this.inquiryScreenGuid = inquiryScreenGuid;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }
}
