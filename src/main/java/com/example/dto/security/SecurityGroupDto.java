package com.example.dto.security;

import java.util.ArrayList;
import java.util.List;

public class SecurityGroupDto {
    private String securityGroupGuid;
    private String groupName;
    private List<CompanyAuthDto> companies = new ArrayList<>();

    public SecurityGroupDto() {}

    public SecurityGroupDto(String securityGroupGuid, String groupName) {
        this.securityGroupGuid = securityGroupGuid;
        this.groupName = groupName;
    }

    public String getSecurityGroupGuid() { return securityGroupGuid; }
    public void setSecurityGroupGuid(String securityGroupGuid) { this.securityGroupGuid = securityGroupGuid; }
    public String getGroupName() { return groupName; }
    public void setGroupName(String groupName) { this.groupName = groupName; }
    public List<CompanyAuthDto> getCompanies() { return companies; }
    public void setCompanies(List<CompanyAuthDto> companies) { this.companies = companies; }
}
