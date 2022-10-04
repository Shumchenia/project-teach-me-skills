package com.example.projectteachmeskills.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(name ="user_name")
    private String userName;

    @NotBlank
    private String password;

    private String role;

    private LocalDateTime localDateTime = LocalDateTime.now();
}