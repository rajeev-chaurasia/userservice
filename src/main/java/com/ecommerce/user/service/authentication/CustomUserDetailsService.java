package com.ecommerce.user.service.authentication;

import com.ecommerce.user.beans.User;
import com.ecommerce.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userEmail) {
        User existingUser = userService.findByEmail(userEmail);

        return org.springframework.security.core.userdetails.User.builder()
                .username(existingUser.getEmail())
                .password(existingUser.getPassword())
                .build();
    }
}
