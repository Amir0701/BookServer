package com.book.bookshareserver.domain.security.auth;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${jwt.token.secret}")
    private String secret;

    public String createToken(Long userId, Long validityTimeInMs){
        Claims claims = Jwts.claims();

        Date now = new Date();
        Date expiresAt = new Date(now.getTime() + validityTimeInMs);

        claims.put("userId", userId);

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(expiresAt)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Long getUserId(@NonNull String token){
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        return claimsJws.getBody().get("userId", Long.class);
    }

    public LocalDateTime getExpirationDate(@NonNull String token){
        Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        Date expiresAt = claims.getBody().getExpiration();

        return LocalDateTime.ofInstant(expiresAt.toInstant(), ZoneId.systemDefault());
    }

    public boolean isExpired(@NonNull String token){
        return LocalDateTime.now().isAfter(getExpirationDate(token));
    }
}
