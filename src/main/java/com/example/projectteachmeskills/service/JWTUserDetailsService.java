package com.example.projectteachmeskills.service;

import com.example.projectteachmeskills.config.jwt.GenerateJWTUser;
import com.example.projectteachmeskills.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JWTUserDetailsService implements UserDetailsService {
    private final UserService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = service.findByUsername(username);

        if (user == null){
            throw new UsernameNotFoundException("User with username: " + username + "not found");
        }

        return GenerateJWTUser.create(user);
    }
}

