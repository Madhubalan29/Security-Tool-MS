package com.example.dto.security;

public class ButtonInfoDto {
    private String buttonGuid;
    private String buttonName;

    public ButtonInfoDto() {}

    public ButtonInfoDto(String buttonGuid, String buttonName) {
        this.buttonGuid = buttonGuid;
        this.buttonName = buttonName;
    }

    public String getButtonGuid() {
        return buttonGuid;
    }

    public void setButtonGuid(String buttonGuid) {
        this.buttonGuid = buttonGuid;
    }

    public String getButtonName() {
        return buttonName;
    }

    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }
}
