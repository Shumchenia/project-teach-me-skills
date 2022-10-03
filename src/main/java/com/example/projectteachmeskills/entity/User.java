package com.example.projectteachmeskills.entity;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@Table(name = "users")

public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Min(value = 4)
    @Max(value = 50)
    @NotEmpty
    @Column(name = "login")
    private String login;

    @Column(name ="password")
    private String password;



}
