package com.book.bookshareserver.domain.service;

import com.book.bookshareserver.data.model.RefreshToken;
import com.book.bookshareserver.data.model.User;

public interface RefreshTokenService {
    RefreshToken createRefreshTokenEntity(String refreshToken, Long validityTimeInMs, User user);

    void save(RefreshToken refreshToken);
}
