package com.ecommerce.user.service;

import com.ecommerce.user.beans.User;
import com.ecommerce.user.dto.*;

public interface UserService {

    UserInfoResponseDto registerUser(UserRegistrationRequestDto registrationRequest);

    UserInfoResponseDto updateUser(Long userId, UserUpdateRequestDto updateRequest);

    void changePassword(Long userId, ChangePasswordRequestDto changePasswordRequest);

    UserInfoResponseDto getUserProfileByEmail(String emailAddress);

    void deleteUser(Long userId);

    User findById(Long userId);

    User findByEmail(String emailAddress);
}
