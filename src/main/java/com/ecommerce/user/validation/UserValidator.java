package com.ecommerce.user.validation;

import com.ecommerce.user.exceptions.TBaseError;
import com.ecommerce.user.exceptions.TBaseRuntimeException;
import com.ecommerce.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserValidator {

    private final UserRepository userRepository;

    public boolean validateUniqueEmailAddress(String emailAddress) {
        if(!userRepository.existsByEmail(emailAddress))
            throw new TBaseRuntimeException(TBaseError.nonUniqueEmailAddress);

        return true;
    }

}

