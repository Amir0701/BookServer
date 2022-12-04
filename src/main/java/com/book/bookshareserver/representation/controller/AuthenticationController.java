package com.book.bookshareserver.representation.controller;

import com.book.bookshareserver.domain.service.AuthenticationService;
import com.book.bookshareserver.representation.dto.RefreshTokenDto;
import com.book.bookshareserver.representation.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<RefreshTokenDto> signUp(@RequestBody @Valid UserDto userDto,
                                                  BindingResult bindingResult)
            throws ExecutionException, InterruptedException {
        RefreshTokenDto refreshTokenDto = authenticationService.register(userDto, bindingResult).get();

        return ResponseEntity.ok(refreshTokenDto);
    }

    @PostMapping("/login")
    public ResponseEntity<RefreshTokenDto> login(@RequestBody UserDto userDto)
            throws ExecutionException, InterruptedException {
        RefreshTokenDto refreshTokenDto = authenticationService.login(userDto).get();

        return ResponseEntity.ok(refreshTokenDto);
    }
}
