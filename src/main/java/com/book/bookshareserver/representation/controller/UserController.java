package com.book.bookshareserver.representation.controller;

import com.book.bookshareserver.domain.service.UserService;
import com.book.bookshareserver.representation.dto.PasswordDto;
import com.book.bookshareserver.representation.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id){
        UserDto userDto = userService.getUserById(id);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping(value = "/favorite", params = {"userId", "publicationId"})
    public void addPublicationFavorite(@RequestParam Long userId,
                                       @RequestParam Long publicationId){
        userService.addPublicationToFavorite(userId, publicationId);
    }

    @GetMapping
    public ResponseEntity<UserDto> getAuthUser(){
        return ResponseEntity.ok(userService.getAuthUser());
    }

    @PutMapping("/password")
    public ResponseEntity<UserDto> changePassword(@RequestBody PasswordDto passwordDto,
                                                  BindingResult bindingResult){
        return ResponseEntity.ok(userService.changePassword(passwordDto, bindingResult));
    }

    @PutMapping("/edit")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,
                                              BindingResult bindingResult){
        return ResponseEntity.ok(userService.updateUser(userDto, bindingResult));
    }
}
