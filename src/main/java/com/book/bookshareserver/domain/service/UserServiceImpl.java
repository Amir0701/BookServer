package com.book.bookshareserver.domain.service;

import com.book.bookshareserver.data.model.User;
import com.book.bookshareserver.data.repository.UserRepository;
import com.book.bookshareserver.representation.dto.UserDto;
import com.book.bookshareserver.representation.dto.converter.UserDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityExistsException;
import java.util.Collections;

@Component
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserDtoConverter userDtoConverter,
                           PasswordEncoder passwordEncoder){
        this.userDtoConverter = userDtoConverter;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User save(UserDto user) {
        User newUser = userDtoConverter.toUser(user);
        newUser.setPublicationList(Collections.emptyList());
        newUser.setRefreshTokenList(Collections.emptyList());
        checkUniqueEmail(newUser);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userRepository.saveAndFlush(newUser);
        return newUser;
    }

    private void checkUniqueEmail(User user){
        if(userRepository.existsByEmail(user.getEmail())){
            throw new EntityExistsException();
        }
    }
}
