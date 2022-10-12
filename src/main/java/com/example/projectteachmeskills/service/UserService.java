package com.example.projectteachmeskills.service;

import com.example.projectteachmeskills.entity.Role;
import com.example.projectteachmeskills.entity.Status;
import com.example.projectteachmeskills.entity.User;
import com.example.projectteachmeskills.repositrory.RoleRepository;
import com.example.projectteachmeskills.repositrory.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public void registration(User user) {
        List<Role> roles = new ArrayList<>();
        Role role = new Role();
        role.setName("USER");
        roles.add(role);
        user.setRoleList(roles);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoleList(roles);
        user.setStatus(Status.ACTIVE);
        role.setUser(user);
        User regUser = userRepository.save(user);
        roleRepository.save(role);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User with username: " + username + " not found"));
    }

    public boolean existByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

}

