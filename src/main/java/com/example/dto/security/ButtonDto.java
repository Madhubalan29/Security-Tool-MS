package com.example.dto.security;

public class ButtonDto {
    private String buttonGuid;

    public ButtonDto() {}

    public ButtonDto(String buttonGuid) {
        this.buttonGuid = buttonGuid;
    }

    public String getButtonGuid() { return buttonGuid; }
    public void setButtonGuid(String buttonGuid) { this.buttonGuid = buttonGuid; }
}
