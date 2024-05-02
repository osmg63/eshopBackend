package com.hackathon.mobil.dto;

public class AuthResponse {

    private String accessToken;
    private String refreshToken; // Opsiyonel: refresh token'ı ekleyebilirsiniz
    private String userId;

    // Getter ve Setter metotları

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
