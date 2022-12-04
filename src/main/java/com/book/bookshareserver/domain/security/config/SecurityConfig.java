package com.book.bookshareserver.domain.security.config;

import com.book.bookshareserver.domain.security.auth.AccessTokenGetter;
import com.book.bookshareserver.domain.security.auth.JwtAuthenticationFilter;
import com.book.bookshareserver.domain.security.auth.JwtAuthenticationProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.PostConstruct;
import javax.servlet.Filter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    private final AccessTokenGetter accessTokenResolver;
//    private final JwtAuthenticationProvider jwtAuthenticationProvider;
//
//    private Filter authenticationFilter;
//
//    @PostConstruct
//    private void initializeFilters() {
//        authenticationFilter = new JwtAuthenticationFilter(jwtAuthenticationProvider, accessTokenResolver);
//    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/auth/**").permitAll()
                .and()
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
