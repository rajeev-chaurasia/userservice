package com.ecommerce.user.service;

import com.ecommerce.user.beans.User;
import com.ecommerce.user.dto.UserInfoResponseDto;
import com.ecommerce.user.exceptions.TBaseError;
import com.ecommerce.user.exceptions.TBaseRuntimeException;
import com.ecommerce.user.repo.UserRepository;
import com.ecommerce.user.utils.MapperUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserInfoResponseDto findByEmail(String emailAddress) {
        if(!StringUtils.hasLength(emailAddress))
            throw new TBaseRuntimeException(TBaseError.invalidEmailAddress);

        User user = userRepository.findByEmail(emailAddress);

        if(Objects.isNull(user)) {
            throw new TBaseRuntimeException(TBaseError.userNotFound);
        }
        return MapperUtils.convertUserToResponseDto(user);
    }
}
