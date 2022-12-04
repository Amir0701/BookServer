package com.book.bookshareserver.domain.service;

import com.book.bookshareserver.data.model.RefreshToken;
import com.book.bookshareserver.data.model.User;
import com.book.bookshareserver.data.repository.RefreshTokenRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
public class RefreshTokenServiceImpl implements RefreshTokenService{
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenServiceImpl(RefreshTokenRepository refreshTokenRepository){
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    public RefreshToken createRefreshTokenEntity(String refreshToken, Long validityTimeInMs, User user) {
        RefreshToken refreshTokenEnt = new RefreshToken();
        refreshTokenEnt.setToken(refreshToken);
        refreshTokenEnt.setUser(user);
        refreshTokenEnt.setExpiresAt(LocalDateTime.now().plus(validityTimeInMs, ChronoUnit.MILLIS));

        return refreshTokenEnt;
    }

    @Override
    public void save(RefreshToken refreshToken) {
        refreshTokenRepository.save(refreshToken);
    }
}
