package com.book.bookshareserver.domain.security.auth;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
public class AccessTokenGetter {
    public Optional<String> getToken(@NonNull HttpServletRequest httpServletRequest){
        String bearerToken = httpServletRequest.getHeader("Authorization");

        if(bearerToken != null && bearerToken.startsWith("Bearer ")){
            return Optional.of(bearerToken.substring(7));
        }

        return Optional.empty();
    }
}
