package com.book.bookshareserver.domain.security.auth;

import com.book.bookshareserver.data.model.User;
import com.book.bookshareserver.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    @Autowired
    public JwtAuthenticationProvider(JwtTokenProvider jwtTokenProvider,
                                     UserRepository userRepository){
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = authentication.getCredentials().toString();

        Long userId = jwtTokenProvider.getUserId(token);

        if(jwtTokenProvider.isExpired(token)){
            throw new BadCredentialsException("The token is not valid");
        }

        User currentUser = userRepository.findById(userId).orElseThrow(() -> {
            throw new BadCredentialsException("The token is valid, but user not found");
        });

        Authentication authenticationToken = new JwtAuthenticationToken(token, currentUser);
        authenticationToken.setAuthenticated(true);
        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.equals(authentication);
    }
}
