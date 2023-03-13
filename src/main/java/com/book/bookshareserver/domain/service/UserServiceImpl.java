package com.book.bookshareserver.domain.service;

import com.book.bookshareserver.data.model.User;
import com.book.bookshareserver.data.repository.UserRepository;
import com.book.bookshareserver.domain.exception.InvalidEntityException;
import com.book.bookshareserver.representation.dto.PasswordDto;
import com.book.bookshareserver.representation.dto.UserDto;
import com.book.bookshareserver.representation.dto.converter.UserDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.persistence.EntityExistsException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

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
        User regUser = userRepository.saveAndFlush(newUser);
        return regUser;
    }

    @Override
    public UserDto getUserById(Long id) throws UsernameNotFoundException{
        User currentUser = getCurrentUser();

        if(currentUser.getId() != id){
            throw new UsernameNotFoundException("");
        }

        User user = userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("Пользователь не найден")
        );

        UserDto userDto = userDtoConverter.toUserDto(user);
        return userDto;
    }

    @Override
    public void addPublicationToFavorite(Long userId, Long publicationId) {
        userRepository.markPublicationAsFavorite(userId, publicationId);
    }

    @Override
    public User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public UserDto getAuthUser() {
        User user = getCurrentUser();
        return userDtoConverter.toUserDto(user);
    }

    @Override
    public UserDto changePassword(PasswordDto passwordDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new InvalidEntityException(
                    bindingResult.getFieldErrors()
                            .stream()
                            .map(FieldError::getDefaultMessage)
                            .collect(Collectors.toList())
            );
        }

        User user = getCurrentUser();
        if(user.getPassword().equals(passwordEncoder.encode(passwordDto.getOldPassword()))){
            user.setPassword(passwordEncoder.encode(passwordDto.getNewPassword()));
            User updatedUser = userRepository.saveAndFlush(user);
            return userDtoConverter.toUserDto(updatedUser);
        }


        user.setPassword(passwordEncoder.encode(passwordDto.getNewPassword()));
        User updatedUser = userRepository.saveAndFlush(user);
        return userDtoConverter.toUserDto(updatedUser);
        //throw new InvalidEntityException(new ArrayList<>());
    }

    @Override
    public UserDto updateUser(UserDto userDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new InvalidEntityException(
                    bindingResult.getFieldErrors()
                            .stream()
                            .map(FieldError::getDefaultMessage)
                            .collect(Collectors.toList())
            );
        }

        User user = getCurrentUser();
        User updatedUser = userDtoConverter.toUser(userDto);
        user.setPhoneNumber(updatedUser.getPhoneNumber());
        user.setEmail(updatedUser.getEmail());
        user.setName(updatedUser.getName());

        userRepository.saveAndFlush(user);
        return userDtoConverter.toUserDto(updatedUser);
    }

    private void checkUniqueEmail(User user){
        if(userRepository.existsByEmail(user.getEmail())){
            throw new EntityExistsException();
        }
    }
}
