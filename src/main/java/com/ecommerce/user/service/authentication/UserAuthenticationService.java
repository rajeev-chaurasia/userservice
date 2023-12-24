package com.ecommerce.user.service.authentication;

import com.ecommerce.user.dto.LoginRequestDto;
import com.ecommerce.user.dto.LoginResponseDto;
import com.ecommerce.user.dto.UserInfoResponseDto;

public interface UserAuthenticationService {

    LoginResponseDto login(LoginRequestDto loginRequest);
}
