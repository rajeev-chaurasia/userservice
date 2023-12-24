package com.ecommerce.user.dto;

import lombok.Data;

@Data
public class LoginResponseDto {

    private String userEmail;
    private String token;

}
