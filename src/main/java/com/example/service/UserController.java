package com.example.service;

import com.example.service.entity.UserEntity;
import com.example.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping
    public ResponseEntity<?> getUser() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping ("/filtered")
    public ResponseEntity<?> getFilteredUser() {
        return new ResponseEntity<>(userRepository.findById(1L), HttpStatus.OK);
    }

//    @GetMapping
//    UserDto getUser() {
//
//        return UserDto.builder()
//                .id(12)
//                .name("Some user name")
//                .build();
//    }

    @PostMapping
    UserDto addUser() {

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("Fist name");
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
