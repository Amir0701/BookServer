package com.book.bookshareserver.representation.dto;

public class RefreshTokenDto {
    private Long userId;
    private String refreshToken;
    private String accessToken;

    public RefreshTokenDto(Long userId, String accessToken, String refreshToken){
        this.userId = userId;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
