package com.ecommerce.user.service;

import com.ecommerce.user.dto.UserInfoResponseDto;

public interface UserService {

    UserInfoResponseDto findByEmail(String emailAddress);

}
