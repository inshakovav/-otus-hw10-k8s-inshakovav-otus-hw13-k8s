package com.example.service.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user", schema = "otus")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
