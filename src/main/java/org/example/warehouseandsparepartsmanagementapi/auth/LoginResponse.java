package org.example.warehouseandsparepartsmanagementapi.auth;

public class LoginResponse {

    private final String accessToken;
    private final String tokenType;

    public LoginResponse(String accessToken, String tokenType) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }
}
