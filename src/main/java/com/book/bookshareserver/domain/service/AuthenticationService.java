package com.book.bookshareserver.domain.service;

import com.book.bookshareserver.representation.dto.RefreshTokenDto;
import com.book.bookshareserver.representation.dto.UserDto;
import org.springframework.validation.BindingResult;

import java.util.concurrent.CompletableFuture;

public interface AuthenticationService {
    CompletableFuture<RefreshTokenDto> login(UserDto credentials);

    CompletableFuture<RefreshTokenDto> register(UserDto userDto, BindingResult bindingResult);
}
