package com.example.projectteachmeskills;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

@SpringBootApplication
@EnableScheduling
public class ProjectTeachMeSkillsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProjectTeachMeSkillsApplication.class, args);
    }
}
