package com.pojos;

public class RefreshLoginPOJO_v2 {
    private String OrganizationId;
    private String Email;
    private String accessToken;
    private String RefreshToken;
    private boolean InitialLogin;


    public void setOrganizationId(String organizationId) {
        this.OrganizationId = organizationId;
    }
    public String getOrganizationId() {
        return OrganizationId;
    }

    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        this.Email = email;
    }

    public String getAccessToken() {
        return accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return RefreshToken;
    }
    public void setRefreshToken(String refreshToken) {
        this.RefreshToken = refreshToken;
    }

    public boolean getInitialLogin() {
        return InitialLogin;
    }
    public void setInitialLogin(boolean initialLogin) {
        this.InitialLogin = initialLogin;
    }
}

