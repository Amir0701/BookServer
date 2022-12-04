package com.book.bookshareserver.domain.security.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

@Component
public class JwtAuthenticationFilter extends GenericFilterBean {
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final AccessTokenGetter accessTokenGetter;

    @Autowired
    public JwtAuthenticationFilter(JwtAuthenticationProvider jwtAuthenticationProvider,
                                    AccessTokenGetter accessTokenGetter){
        this.jwtAuthenticationProvider = jwtAuthenticationProvider;
        this.accessTokenGetter = accessTokenGetter;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Optional<String> accessTokenOptional = accessTokenGetter.getToken((HttpServletRequest) servletRequest);

        if(accessTokenOptional.isPresent()) {
            String accessToken = accessTokenOptional.get();

            try {
                Authentication authentication = jwtAuthenticationProvider.authenticate(
                        new JwtAuthenticationToken(accessToken)
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (AuthenticationException e) {
                e.printStackTrace();
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
