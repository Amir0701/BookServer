package com.book.bookshareserver.domain.service;

import com.book.bookshareserver.data.model.RefreshToken;
import com.book.bookshareserver.data.model.User;
import com.book.bookshareserver.data.repository.UserRepository;
import com.book.bookshareserver.domain.exception.InvalidEntityException;
import com.book.bookshareserver.domain.security.auth.JwtTokenProvider;
import com.book.bookshareserver.representation.dto.RefreshTokenDto;
import com.book.bookshareserver.representation.dto.UserDto;
import com.book.bookshareserver.representation.dto.converter.RefreshTokenDtoConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
public class AuthenticationServiceImpl implements AuthenticationService{
    @Value("${accessToken.validityTimeMs}")
    private Long accessTokenValidityTimeMs;

    @Value("${refreshToken.validityTimeMs}")
    private Long refreshTokenValidityTimeMs;

    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenDtoConverter refreshTokenDtoConverter;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;
    private UserRepository userRepository;

    public AuthenticationServiceImpl(JwtTokenProvider jwtTokenProvider,
                                     PasswordEncoder passwordEncoder,
                                     RefreshTokenDtoConverter refreshTokenDtoConverter,
                                     RefreshTokenService refreshTokenService,
                                     UserService userService,
                                     UserRepository userRepository){
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.refreshTokenDtoConverter = refreshTokenDtoConverter;
        this.refreshTokenService = refreshTokenService;
        this.userService = userService;
        this.userRepository = userRepository;
    }


    @Async
    @Override
    public CompletableFuture<RefreshTokenDto> login(UserDto credentials) throws BadCredentialsException {
        String exceptionMsg = "Couldn't find the user with provided email and password";

        User user = Optional.ofNullable(userRepository.findByEmail(credentials.getEmail()))
                .orElseThrow(
                        () -> new BadCredentialsException(exceptionMsg)
                );

        if(!passwordEncoder.matches(credentials.getPassword(), user.getPassword())){
            throw new BadCredentialsException(exceptionMsg);
        }

        return CompletableFuture.completedFuture(generateAndSaveSecurityTokens(user));
    }

    @Override
    public CompletableFuture<RefreshTokenDto> register(UserDto userDto, BindingResult bindingResult)
            throws InvalidEntityException  {

        if(bindingResult.hasErrors()) {
            throw new InvalidEntityException(
                    bindingResult.getFieldErrors().stream()
                            .map(FieldError::getDefaultMessage)
                            .collect(Collectors.toList())
            );
        }

        User regUser = userService.save(userDto);

        return CompletableFuture.completedFuture(generateAndSaveSecurityTokens(regUser));
    }

    private RefreshTokenDto generateAndSaveSecurityTokens(User user){
        String accessToken = jwtTokenProvider.createToken(user.getId(), accessTokenValidityTimeMs);
        String refreshToken = jwtTokenProvider.createToken(user.getId(), refreshTokenValidityTimeMs);

        RefreshToken refreshTokenEnt = refreshTokenService.createRefreshTokenEntity(
                refreshToken, refreshTokenValidityTimeMs, user
        );
        refreshTokenService.save(refreshTokenEnt);

        return refreshTokenDtoConverter.toRefreshTokenDto(user, accessToken, refreshToken);
    }
}
