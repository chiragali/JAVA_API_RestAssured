package com.pojos;

public class LoginPOJO {
    private String OrganizationId;
    private String Email;
    private String Password;

    // getters and setters
    public String getOrganizationId() {
        return OrganizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.OrganizationId = organizationId;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }
}

