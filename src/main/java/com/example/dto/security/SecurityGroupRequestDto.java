package com.example.dto.security;

public class SecurityGroupRequestDto {
    private SecurityGroupDto securityGroup;

    public SecurityGroupRequestDto() {}

    public SecurityGroupRequestDto(SecurityGroupDto securityGroup) {
        this.securityGroup = securityGroup;
    }

    public SecurityGroupDto getSecurityGroup() {
        return securityGroup;
    }

    public void setSecurityGroup(SecurityGroupDto securityGroup) {
        this.securityGroup = securityGroup;
    }
}
