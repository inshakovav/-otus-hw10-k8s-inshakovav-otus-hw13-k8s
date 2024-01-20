package com.example.service;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping
    UserDto getUser() {

        return UserDto.builder()
                .id(12)
                .name("Some user name")
                .build();
    }

    @PostMapping
    UserDto addUser() {
        return UserDto.builder()
                .id(12)
                .name("Some user name")
                .build();
    }

    @DeleteMapping
    UserDto deleteUser() {
        return UserDto.builder()
                .id(12)
                .name("Some user name")
                .build();
    }
}
