package com.ecommerce.user.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class ChangePasswordRequestDto {

    @NotBlank(message = "Old Password is required.")
    private String oldPassword;

    @NotBlank(message = "New Password is required.")
    @Size(min = 8, message = "Password must be at least 8 characters long.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Password must contain at least one digit, one lowercase and one uppercase letter, one special character, and no whitespaces")
    private String newPassword;
}
