package com.pojos;

public class LoginPOJO_v2 {
    private int OrganizationId;
    private String Email;
    private String OrganizationType;

    // getters and setters
    public int getOrganizationId() {
        return OrganizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.OrganizationId = organizationId;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getPassword() {
        return OrganizationType;
    }

    public void setPassword(String password) {
        this.OrganizationType = password;
    }
}

