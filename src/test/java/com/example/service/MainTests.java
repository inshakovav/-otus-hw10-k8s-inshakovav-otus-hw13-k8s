package com.example.service;

import com.example.service.entity.UserEntity;
import com.example.service.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MainTests {

    @Autowired
    UserRepository userRepository;

//    @Test
    void contextLoads() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("Fist name");
        userRepository.save(userEntity);
    }

}
