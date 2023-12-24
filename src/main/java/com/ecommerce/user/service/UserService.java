package com.ecommerce.user.service;

import com.ecommerce.user.dto.*;

public interface UserService {

    UserInfoResponseDto registerUser(UserRegistrationRequestDto registrationRequest);

    UserInfoResponseDto updateUser(Long userId, UserUpdateRequestDto updateRequest);

    void changePassword(Long userId, ChangePasswordRequestDto changePasswordRequest);

    UserInfoResponseDto findByEmail(String emailAddress);

    void deleteUser(Long userId);
}
