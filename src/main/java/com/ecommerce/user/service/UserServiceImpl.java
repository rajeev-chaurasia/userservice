package com.ecommerce.user.service;

import com.ecommerce.user.beans.User;
import com.ecommerce.user.dto.ChangePasswordRequestDto;
import com.ecommerce.user.dto.UserInfoResponseDto;
import com.ecommerce.user.dto.UserRegistrationRequestDto;
import com.ecommerce.user.dto.UserUpdateRequestDto;
import com.ecommerce.user.exceptions.TBaseError;
import com.ecommerce.user.exceptions.TBaseRuntimeException;
import com.ecommerce.user.repo.UserRepository;
import com.ecommerce.user.utils.MapperUtils;
import com.ecommerce.user.validation.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserValidator userValidator;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserInfoResponseDto registerUser(UserRegistrationRequestDto registrationRequest) {
        userValidator.validateUniqueEmailAddress(registrationRequest.getEmail());
        String encodedPassword = passwordEncoder.encode(registrationRequest.getPassword());
        User newUser = MapperUtils.convertUserRegistrationRequestDtoToUser(registrationRequest);
        newUser.setPassword(encodedPassword);
        User savedUser = userRepository.save(newUser);
        return MapperUtils.convertUserToUserInfoResponseDto(savedUser);
    }

    @Override
    public UserInfoResponseDto updateUser(Long userId, UserUpdateRequestDto updateRequest) {
        User existingUser = findById(userId);

        if(StringUtils.hasLength(updateRequest.getFirstName())) {
            existingUser.setFirstName(updateRequest.getFirstName());
        }

        if(StringUtils.hasLength(updateRequest.getLastName())) {
            existingUser.setLastName(updateRequest.getLastName());
        }

        if(StringUtils.hasLength(updateRequest.getEmail()) && userValidator.validateUniqueEmailAddress(updateRequest.getEmail())) {
            existingUser.setEmail(updateRequest.getEmail());
        }

        if(Objects.nonNull(updateRequest.getAddress())) {
            existingUser.getAddress().update(updateRequest.getAddress());
        }

        if(Objects.nonNull(updateRequest.getContact())) {
            existingUser.getContact().update(updateRequest.getContact());
        }

        User updatedUser = userRepository.save(existingUser);
        return MapperUtils.convertUserToUserInfoResponseDto(updatedUser);
    }

    @Override
    public void changePassword(Long userId, ChangePasswordRequestDto changePasswordRequest) {
        User existingUser = findById(userId);

        if(!passwordEncoder.matches(changePasswordRequest.getOldPassword(), existingUser.getPassword())) {
            throw new TBaseRuntimeException(TBaseError.oldPasswordNotMatched);
        }

        String newEncodedPassword = passwordEncoder.encode(changePasswordRequest.getNewPassword());
        existingUser.setPassword(newEncodedPassword);

        userRepository.save(existingUser);
    }

    @Override
    public UserInfoResponseDto getUserProfileByEmail(String emailAddress) {
        User existingUser = findByEmail(emailAddress);
        return MapperUtils.convertUserToUserInfoResponseDto(existingUser);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public User findById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()) {
            throw new TBaseRuntimeException(TBaseError.userNotFound);
        }

        return user.get();
    }

    public User findByEmail(String emailAddress) {
        Optional<User> user = userRepository.findByEmail(emailAddress);

        if(user.isEmpty()) {
            throw new TBaseRuntimeException(TBaseError.userNotFound);
        }

        return user.get();
    }

}
