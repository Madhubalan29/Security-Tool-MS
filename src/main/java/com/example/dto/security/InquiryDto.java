package com.example.dto.security;

public class InquiryDto {
    private String inquiryScreenNameGuid;

    public InquiryDto() {}

    public InquiryDto(String inquiryScreenNameGuid) {
        this.inquiryScreenNameGuid = inquiryScreenNameGuid;
    }

    public String getInquiryScreenNameGuid() { return inquiryScreenNameGuid; }
    public void setInquiryScreenNameGuid(String inquiryScreenNameGuid) { this.inquiryScreenNameGuid = inquiryScreenNameGuid; }
}
