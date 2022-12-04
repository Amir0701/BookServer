package com.book.bookshareserver.domain.security.auth;

import com.book.bookshareserver.data.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Collections;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {
    private String token;
    private User user;


    public JwtAuthenticationToken(String token, User user){
        super(Collections.emptyList());
        this.token = token;
        this.user = user;
    }

    public JwtAuthenticationToken(String token){
        super(Collections.emptyList());
        this.token = token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return user;
    }
}
