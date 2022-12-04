package com.book.bookshareserver.representation.dto.converter.impl;

import com.book.bookshareserver.data.model.RefreshToken;
import com.book.bookshareserver.data.model.User;
import com.book.bookshareserver.representation.dto.RefreshTokenDto;
import com.book.bookshareserver.representation.dto.converter.RefreshTokenDtoConverter;
import org.springframework.stereotype.Component;

@Component
public class RefreshTokenDtoConverterImpl implements RefreshTokenDtoConverter {
    @Override
    public RefreshTokenDto toRefreshTokenDto(User user,
                                             String accessToken,
                                             String refreshToken) {
        return new RefreshTokenDto(user.getId(), accessToken, refreshToken);
    }
}
