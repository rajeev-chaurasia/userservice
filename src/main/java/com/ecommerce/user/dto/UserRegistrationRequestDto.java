package com.ecommerce.user.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Data
public class UserRegistrationRequestDto {

    @NotBlank(message = "First name is required.")
    private String firstName;

    @NotBlank(message = "Last name is required.")
    private String lastName;

    @NotBlank(message = "Email address is required.")
    @Email(message = "Invalid email address")
    private String email;

    @NotNull(message = "Contact is required.")
    @Valid
    private ContactDto contact;

    @NotBlank(message = "Password is required.")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Password must contain at least one digit, one lowercase and one uppercase letter, one special character, and no whitespaces")
    private String password;

    @NotNull(message = "Address is required.")
    @Valid
    private AddressDto address;
}
