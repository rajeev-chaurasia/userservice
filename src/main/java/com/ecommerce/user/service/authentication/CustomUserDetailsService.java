package com.ecommerce.user.service.authentication;

import com.ecommerce.user.beans.User;
import com.ecommerce.user.exceptions.TBaseError;
import com.ecommerce.user.exceptions.TBaseRuntimeException;
import com.ecommerce.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userEmail) {
        Optional<User> user = userRepository.findByEmail(userEmail);

        if(user.isEmpty()) {
            throw new TBaseRuntimeException(TBaseError.userNotFound);
        }
        User existingUser = user.get();

        return org.springframework.security.core.userdetails.User.builder()
                .username(existingUser.getEmail())
                .password(existingUser.getPassword())
                .build();
    }
}
