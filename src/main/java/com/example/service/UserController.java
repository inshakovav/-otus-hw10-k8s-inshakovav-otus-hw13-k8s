package com.example.service;

import com.example.service.entity.UserEntity;
import com.example.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping
    UserDto getUser() {

        return UserDto.builder()
                .id(12)
                .name("Some user name")
                .build();
    }

    @PostMapping
    UserDto addUser() {

        UserEntity userEntity = new UserEntity();
        userEntity.setName("Fist name");
        userRepository.save(userEntity);

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
