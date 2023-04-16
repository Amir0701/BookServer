package com.book.bookshareserver.data.repository;

import com.book.bookshareserver.data.model.RefreshToken;
import com.book.bookshareserver.representation.dto.RefreshTokenDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    RefreshToken findRefreshTokenByToken(String token);
}
