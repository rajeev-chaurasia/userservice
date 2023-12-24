package com.ecommerce.user.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class LoginRequestDto {

    @NotBlank(message = "Email address is required.")
    @Email(message =  "Invalid email address")
    private String email;

    @NotBlank(message = "Password is required.")
    private String password;
}
