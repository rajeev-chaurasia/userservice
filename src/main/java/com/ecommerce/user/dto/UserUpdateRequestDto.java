package com.ecommerce.user.dto;

import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class UserUpdateRequestDto {

    private String firstName;

    private String lastName;

    @Email(message = "Invalid email address")
    private String email;

    private ContactDto contact;

    private AddressDto address;

}
