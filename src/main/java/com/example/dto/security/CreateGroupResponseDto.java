package com.example.dto.security;

public class CreateGroupResponseDto {
    private String securityGroupGuid;
    private String groupName;
    private String insertScript;

    public CreateGroupResponseDto() {}

    public CreateGroupResponseDto(String securityGroupGuid, String groupName, String insertScript) {
        this.securityGroupGuid = securityGroupGuid;
        this.groupName = groupName;
        this.insertScript = insertScript;
    }

    public String getSecurityGroupGuid() { return securityGroupGuid; }
    public void setSecurityGroupGuid(String securityGroupGuid) { this.securityGroupGuid = securityGroupGuid; }
    public String getGroupName() { return groupName; }
    public void setGroupName(String groupName) { this.groupName = groupName; }
    public String getInsertScript() { return insertScript; }
    public void setInsertScript(String insertScript) { this.insertScript = insertScript; }
}
