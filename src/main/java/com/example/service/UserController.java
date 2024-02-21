package com.example.service;

import com.example.service.entity.UserEntity;
import com.example.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping
    public ResponseEntity<?> getUser() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public UserEntity getUserById(@PathVariable Long userId) {
        return userRepository.findById(userId).get();
    }

    @PostMapping
    void addUser(@RequestBody UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    @PutMapping("/{userId}")
    UserEntity updateUser(@RequestBody UserEntity newUser, @PathVariable Long userId) {
        return userRepository.findById(userId)
                .map(userEntity -> {
                    userEntity.setUsername(newUser.getUsername());
                    userEntity.setFirstName(newUser.getFirstName());
                    userEntity.setLastName(newUser.getLastName());
                    userEntity.setEmail(newUser.getEmail());
                    userEntity.setPhone(newUser.getPhone());
                    return userRepository.save(userEntity);
                })
                .orElseGet(() -> userRepository.save(newUser));
    }

    @DeleteMapping("/{userId}")
    DeleteDto deleteUser(@PathVariable Long userId) {
        userRepository.deleteById(userId);
        return DeleteDto.builder()
                .code(0)
                .message("User has been deleted")
                .build();
    }
}
