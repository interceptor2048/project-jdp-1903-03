package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.UserDto;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @GetMapping("getUsers")
    public List<UserDto> getUsers() {
        return userMapper.mapToUserDtoList(userService.getUsersList());
    }

    @GetMapping("getUser")
    public UserDto getUser(@RequestParam Long userId) throws UserNotFoundException {
        return userMapper.mapToUserDto(userService.getUser(userId).orElseThrow(UserNotFoundException::new));
    }

    @PostMapping("createUser")
    public void createUser(@RequestBody UserDto userDto) {
        userService.createUser(userMapper.mapToUser(userDto));
    }

    @PutMapping("blockUser")
    public UserDto blockUser(@RequestParam Long userId, @RequestBody UserDto userDto) {
        return new UserDto(userId, userDto.getUserName(), true, userDto.getUserIdKey());
    }

    @GetMapping("generateUserIdKey")
    public Long generateUserIdKey(@RequestParam Long userId) {
        throw new UnsupportedOperationException("This operation is not yet supported.");
    }
}